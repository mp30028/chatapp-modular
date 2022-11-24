package com.zonesoft.chats.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.zonesoft.chats.data_generators.ConversationDataGenerator.*;

class ConversationTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConversationTest.class);
	
	@Test
	void test_InstantiatingConversation() {
		Conversation conversation = generateConversation();
		assertNotNull(conversation);
		assertNotNull(conversation.messages());
		assertNotNull(conversation.participants());
		LOGGER.debug("FROM test_InstantiatingConversation: generated-conversation={}", conversation);
	}

}
