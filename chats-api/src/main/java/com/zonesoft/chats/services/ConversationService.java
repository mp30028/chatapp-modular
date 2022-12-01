package com.zonesoft.chats.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zonesoft.chats.models.Conversation;
import com.zonesoft.chats.repositories.ConversationRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ConversationService {
	
	private final ConversationRepository repository;
	
	@Autowired
	public ConversationService(ConversationRepository repository, PersonService personsService) {
		super();
		this.repository = repository;
	}
	
	public Mono<Conversation> insert(Conversation conversation){
		return repository.insert(conversation);
	}
	
	public Mono<Conversation> update(Conversation conversation){
		return repository.save(conversation);
	}
	
	public Flux<Conversation> saveAll(List<Conversation> conversations){
    	return repository.saveAll(conversations);
    }
    
	public Mono<Conversation> findById(String id){
    	return repository.findById(id);
    }
    
	public Flux<Conversation> findAll(){
    	return repository.findAll();
    }
    
	public Mono<Void> deleteAll(){
    	return repository.deleteAll();
    }
    
	public Mono<Void> deleteById(String id){
    	return repository.deleteById(id);
    }
	
//	public Flux<Conversation> findByMoniker(String moniker){
//    	return repository.findByMoniker(moniker);
//    }
	
}
