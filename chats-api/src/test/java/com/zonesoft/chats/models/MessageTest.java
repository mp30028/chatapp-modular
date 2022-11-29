package com.zonesoft.chats.models;

import static com.zonesoft.chats.data_generators.MessageDataGenerator.generateMessageWithId;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class MessageTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageTest.class);
	
	@Test
	void testInstantiation() {
		Message message = generateMessageWithId();
		LOGGER.debug("Generated Message. Instantiated-message = {}", message);
		assertNotNull(message);
		assertNotNull(message.getId());
		assertNotNull(message.getMessageText());
		assertNotNull(message.getSenderParticipantId());
		assertNotNull(message.getSentTime());
	}
}
