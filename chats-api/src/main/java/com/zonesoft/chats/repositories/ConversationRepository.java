package com.zonesoft.chats.repositories;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.zonesoft.chats.models.Conversation;

import reactor.core.publisher.Flux;

@Repository
public interface ConversationRepository extends ReactiveMongoRepository<Conversation, String> {

	  @Query("{ 'participants.moniker' : ?0 }")
	  Flux<Conversation> findByMoniker(String moniker);
}
