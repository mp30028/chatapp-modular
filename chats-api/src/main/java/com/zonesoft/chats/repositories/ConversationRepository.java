package com.zonesoft.chats.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.zonesoft.chats.models.Conversation;

@Repository
public interface ConversationRepository extends ReactiveMongoRepository<Conversation, String> {

}
