package com.zonesoft.chats.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ChangeStreamEvent;
import org.springframework.data.mongodb.core.ChangeStreamOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;

import com.zonesoft.chats.models.Conversation;
import com.zonesoft.chats.repositories.ConversationRepository;
import com.zonesoft.chats.events.PersistenceEvent.PersistenceEventType;
import com.zonesoft.chats.events.PersistenceEvent;
import static com.zonesoft.chats.services.PersistenceEventsLogger.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

@Service
public class ConversationService {
	
	private final ConversationRepository repository;
	private final ReactiveMongoTemplate reactiveTemplate;
	
	@Autowired
	public ConversationService(ConversationRepository repository, PersonService personsService, ReactiveMongoTemplate reactiveTemplate) {
		super();
		this.repository = repository;
		this.reactiveTemplate = reactiveTemplate;
	}
	
	public Mono<Conversation> insert(Conversation conversation){
		return repository.insert(conversation).doFinally(getEventWriter(PersistenceEventType.SAVE, conversation));
	}
	
	public Mono<Conversation> update(Conversation conversation){
		return repository.save(conversation).doFinally(getEventWriter(PersistenceEventType.SAVE, conversation));
	}
	
	public Flux<Conversation> saveAll(List<Conversation> conversations){
    	return repository.saveAll(conversations).doFinally(getEventWriter(PersistenceEventType.SAVE_ALL, conversations));
    }
    
	public Mono<Conversation> findById(String id){
    	return repository.findById(id);
    }
    
	public Flux<Conversation> findAll(){
    	return repository.findAll();
    }
    
	public Mono<Void> deleteAll(){
//    	return repository.deleteAll();
		return findAll()
		.collectList()
		.map((l) -> getEventWriter(PersistenceEventType.DELETE_ALL, l))
		.map((w) -> {w.accept(SignalType.ON_COMPLETE); return true;})
		.then(repository.deleteAll());
    }
    
	public Mono<Void> deleteById(String id){
//    	return repository.deleteById(id);
		return findById(id)
		.map((p) -> getEventWriter(PersistenceEventType.DELETE, p))
		.map((w) -> {w.accept(SignalType.ON_COMPLETE); return true;})
		.then(repository.deleteById(id));
    }
	
//	public Flux<Conversation> findByMoniker(String moniker){
//    	return repository.findByMoniker(moniker);
//    }
	
	public Flux<PersistenceEvent> streamAllEvents() {
        ChangeStreamOptions options = ChangeStreamOptions.builder()
                                                         .returnFullDocumentOnUpdate()
                                                         .build();

        return reactiveTemplate.changeStream("persistenceEvents",options,PersistenceEvent.class)
                               .map(ChangeStreamEvent::getBody);
    }
	
}
