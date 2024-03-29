package com.zonesoft.chats.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ChangeStreamEvent;
import org.springframework.data.mongodb.core.ChangeStreamOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;

import com.zonesoft.chats.models.Conversation;
import com.zonesoft.chats.models.Message;
import com.zonesoft.chats.repositories.ConversationRepository;
import com.zonesoft.chats.events.PersistenceEvent.PersistenceEventType;
import com.zonesoft.chats.events.PersistenceEvent;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

@Service
public class ConversationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConversationService.class);
	private final ConversationRepository repository;
	private final ReactiveMongoTemplate reactiveTemplate;
	private final PersonService personService;
	private final PersistenceEventsLogger eventsLogger;
	
	@Autowired
	public ConversationService(ConversationRepository repository, PersonService personsService,PersistenceEventsLogger eventsLogger, ReactiveMongoTemplate reactiveTemplate) {
		super();
		this.repository = repository;
		this.reactiveTemplate = reactiveTemplate;
		this.personService = personsService;
		this.eventsLogger = eventsLogger;
	}
	
	public Mono<Conversation> insert(Conversation conversation){
		return repository.insert(conversation).doFinally(eventsLogger.getEventWriter(PersistenceEventType.SAVE, conversation));
	}
	
	public Mono<Conversation> update(Conversation conversation){
		return repository.save(conversation).doFinally(eventsLogger.getEventWriter(PersistenceEventType.SAVE, conversation));
	}
	
	public Mono<Conversation> updateWithNewMessage(String id, Message message){
		if (Objects.isNull(message.getId())){
			message.setId(ObjectId.get().toString());
		};
		if (Objects.isNull(message.getSentTime())) {
			message.setSentTime(OffsetDateTime.now());
		};
		Mono<Conversation> updatedConversationMono = 
			findById(id)
				.map((c) -> {
						c.messages().add(message);
						return c;
					})
				.flatMap((c) -> this.update(c));
		return updatedConversationMono;
	}
	
	public Flux<Conversation> saveAll(List<Conversation> conversations){
    	return repository.saveAll(conversations).doFinally(eventsLogger.getEventWriter(PersistenceEventType.SAVE_ALL, conversations));
    }
    
	public Mono<Conversation> findById(String id){
    	return repository.findById(id);
    }
    
	public Flux<Conversation> findAll(){
    	return repository.findAll();
    }
    
	public Mono<Void> deleteAll(){
		return findAll()
			.collectList()
			.map((l) -> eventsLogger.getEventWriter(PersistenceEventType.DELETE_ALL, l))
			.map((w) -> {w.accept(SignalType.ON_COMPLETE); return true;})
			.then(repository.deleteAll());
    }
    
	public Mono<Void> deleteById(String id){
		return findById(id)
			.map((p) -> eventsLogger.getEventWriter(PersistenceEventType.DELETE, p))
			.map((w) -> {w.accept(SignalType.ON_COMPLETE); return true;})
			.then(repository.deleteById(id));
    }
	
	public Flux<Conversation> findByMoniker(String moniker){
		Flux<Conversation> conversationFlux =
			personService
				.fetchPersonIdByMoniker(moniker)
				.flatMap((s) -> {
					LOGGER.debug("ConversationService.findByMoniker:moniker={}, id={}",moniker, s);
					Mono<List<Conversation>> findResult = repository.findByParticipantPersonId(s).collectList();
					return findResult;
				})
				.flatMapMany(l -> {
					LOGGER.debug("ConversationService.findByMoniker: conversations {}",l.toString());
					return Flux.fromIterable(l);
				});
		return conversationFlux;
    }
	
	public Flux<PersistenceEvent> streamAllEvents() {
        ChangeStreamOptions options = ChangeStreamOptions.builder()
                                                         .returnFullDocumentOnUpdate()
                                                         .build();

        return reactiveTemplate.changeStream("persistenceEvents",options,PersistenceEvent.class)
                               .map(ChangeStreamEvent::getBody);
    }
	
}
