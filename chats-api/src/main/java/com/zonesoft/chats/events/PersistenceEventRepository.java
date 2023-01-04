package com.zonesoft.chats.events;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;

import reactor.core.publisher.Flux;

public interface PersistenceEventRepository extends ReactiveMongoRepository<PersistenceEvent, String> {

	  @Tailable
	  @Query("{}")
	  Flux<PersistenceEvent> streamAll();
}
