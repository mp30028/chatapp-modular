package com.zonesoft.chats.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zonesoft.chats.data_generators.MessageRecordBuilder;
import com.zonesoft.utils.data_generators.RecordsGeneratorTemplate;

class MessageTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageTest.class);
	
	@Test
	void testSingleMessageInstantiation() {
		Message message = new MessageRecordBuilder().withDefaults().generate();
		LOGGER.debug("MessageTest.testSingleMessageInstantiation Instantiated-message = {}", message);
		assertNotNull(message);
		assertNotNull(message.getId());
		assertNotNull(message.getMessageText());
		assertNotNull(message.getSenderParticipantId());
		assertNotNull(message.getSentTime());
	}

	@Test
	void testMultipleMessagesInstantiation() {
		Supplier<MessageRecordBuilder> supplier = (()-> new MessageRecordBuilder());
		List<Message> messages = new RecordsGeneratorTemplate<MessageRecordBuilder,Message>().id(true).generate(supplier);
		LOGGER.debug("MessageTest.testMultipleMessagesInstantiation Instantiated-messages = {}", messages);
		for (Message message : messages) {
			assertNotNull(message);
			assertNotNull(message.getId());
			assertNotNull(message.getMessageText());
			assertNotNull(message.getSenderParticipantId());
			assertNotNull(message.getSentTime());
		}
	}
}
