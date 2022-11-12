package com.zonesoft.chats.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ConversationTest extends Conversation {

	@Test
	void test_InstantiatingConversation() {
		Conversation conversation = new Conversation();
		assertNotNull(conversation);
		assertNotNull(conversation.getMessages());
		assertNotNull(conversation.getParticipants());
	}

}
