package com.zonesoft.chats.services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zonesoft.chats.events.PersistenceEvent;
import com.zonesoft.chats.events.PersistenceEvent.PersistenceEventType;
import com.zonesoft.chats.events.PersistenceEventRepository;
import com.zonesoft.chats.models.Conversation;

import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

@Service
public class PersistenceEventsLogger {
	
	private PersistenceEventRepository eventRepository;
	
	@Autowired
	public PersistenceEventsLogger(PersistenceEventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	private void writeEvent(PersistenceEventType eventType, List<Conversation> conversations) {
		PersistenceEvent event = new PersistenceEvent(eventType,conversations);
		Mono<PersistenceEvent> returnResult = eventRepository.insert(event);
		returnResult.subscribe();
	}
	
	public Consumer<SignalType> getEventWriter(PersistenceEventType eventType, Conversation conversation){
		List<Conversation> conversations = new ArrayList<>();
		conversations.add(conversation);
		return getEventWriter(eventType, conversations);
	}
	
	public Consumer<SignalType> getEventWriter(PersistenceEventType eventType, List<Conversation> conversations){
		return (s -> { if (s == SignalType.ON_COMPLETE) {writeEvent(eventType, conversations);};});
	}
	
}
