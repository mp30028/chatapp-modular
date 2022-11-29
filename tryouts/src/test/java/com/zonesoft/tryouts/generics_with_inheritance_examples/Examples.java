package com.zonesoft.tryouts.generics_with_inheritance_examples;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Examples {
	private static final Logger LOGGER = LoggerFactory.getLogger(Examples.class);
	
	@Test
	void runExample01() {
		Message message = new MessageRecordBuilder().withDefaults().generate();
		LOGGER.debug("generics_with_inheritance_examples.Example01 Instantiated-message = {}", message);
	}

	@Test
	void runExample02() {
		Supplier<MessageRecordBuilder> supplier = (()-> new MessageRecordBuilder());
		List<Message> messages = new RecordsGeneratorTemplate<MessageRecordBuilder,Message>().id(true).generate(supplier);
		LOGGER.debug("generics_with_inheritance_examples.Example02 Instantiated-messages = {}", messages);
		for (Message message : messages) {
			assertNotNull(message);
			assertNotNull(message.getId());
			assertNotNull(message.getMessageText());
			assertNotNull(message.getSenderParticipantId());
			assertNotNull(message.getSentTime());
		}
	}
}
