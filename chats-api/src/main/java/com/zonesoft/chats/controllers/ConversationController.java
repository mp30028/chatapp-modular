package com.zonesoft.chats.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zonesoft.chats.events.PersistenceEvent;
import com.zonesoft.chats.models.Conversation;
import com.zonesoft.chats.services.ConversationService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

	private final ConversationService service;
    
    @Autowired
    public ConversationController(ConversationService service) {
    	super();
    	this.service = service;
    }
    @PostMapping
    public Mono<ResponseEntity<Conversation>> insert(@RequestBody Conversation conversation){
    	Mono<Conversation> conversationMono = service.insert(conversation);
        return conversationMono.map(p-> ResponseEntity.created(null).body(p))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
    
    @GetMapping
    public Mono<ResponseEntity<List<Conversation>>> findAll(){
    	Flux<Conversation> conversationFlux = service.findAll();
    	if (Objects.nonNull(conversationFlux)) {
	        return conversationFlux
	        	.collectList()
	        	.map( l -> {
	        		if (Objects.nonNull(l) && (l.size()>0)) {
	        			return ResponseEntity.ok().body(l);	
	        		}else {
	        			return ResponseEntity.noContent().build();
	        		}
	        	});
	    }else {
	    	return Mono.just(ResponseEntity.noContent().build());
	    }
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Conversation>> findById(@PathVariable String id){
        Mono<Conversation> conversationMono = service.findById(id);
        return conversationMono.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    @GetMapping(params = {"moniker"})
    public Mono<ResponseEntity<List<Conversation>>> findByMoniker(@RequestParam String moniker){
    	Flux<Conversation> conversationFlux = service.findByMoniker(moniker);
    	if (Objects.nonNull(conversationFlux)) {
	        return conversationFlux
	        	.collectList()
	        	.map( l -> {
	        		if (Objects.nonNull(l) && (l.size()>0)) {
	        			return ResponseEntity.ok().body(l);	
	        		}else {
	        			return ResponseEntity.noContent().build();
	        		}
	        	});
	    }else {
	    	return Mono.just(ResponseEntity.noContent().build());
	    }
    	
    }
    
    
    
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Conversation>> update(@PathVariable String id, @RequestBody Conversation conversation){
        return service.update(conversation)
                .map( p -> ResponseEntity.accepted().body(p));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable String id){
        return service.deleteById(id)
                .map( r -> ResponseEntity.accepted().<Void>build());
    }
    
    @GetMapping(value = "/persistence-events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PersistenceEvent> streamAllEvents() {
        return service.streamAllEvents();
    }
}
