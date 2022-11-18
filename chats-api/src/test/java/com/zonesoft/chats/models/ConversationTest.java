package com.zonesoft.chats.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static com.zonesoft.utils.data_generators.Generator.*;

class ConversationTest {

	@Test
	void test_InstantiatingConversation() {
		Conversation conversation = new Conversation(generateUUID());
		assertNotNull(conversation);
		assertNotNull(conversation.messages());
		assertNotNull(conversation.participants());
	}

}
