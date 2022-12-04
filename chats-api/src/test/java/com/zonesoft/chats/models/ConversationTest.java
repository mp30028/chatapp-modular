package com.zonesoft.chats.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zonesoft.chats.data_generators.ConversationRecordBuilder;
import com.zonesoft.utils.data_generators.RecordsGeneratorTemplate;

class ConversationTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConversationTest.class);
	
	@Test
	void testSingleConversationInstantiation() {
		Conversation conversation = new ConversationRecordBuilder().withDefaults().build();
		assertNotNull(conversation);
		assertNotNull(conversation.messages());
		assertNotNull(conversation.participants());
		LOGGER.debug("ConversationTest.testSingleConversationInstantiation: generated-conversation={}", conversation);
	}
	
	@Test
	void testMultipleConversationsInstantiation() {
		Supplier<ConversationRecordBuilder> supplier = (()-> new ConversationRecordBuilder().withDefaults());
		List<Conversation> conversations = new RecordsGeneratorTemplate<ConversationRecordBuilder,Conversation>().generate(supplier);
		LOGGER.debug("ConversationTest.testMultipleConversationsInstantiation Instantiated-messages = {}", conversations);
		for (Conversation conversation : conversations) {
			assertNotNull(conversation);
			assertNotNull(conversation.getId());
			assertNotNull(conversation.messages());
			assertNotNull(conversation.participants());
		}
	}
}
