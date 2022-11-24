package com.zonesoft.chats.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.zonesoft.chats.data_generators.PersonDataGenerator.*;

class PersonTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonTest.class);
	
	@Test
	void testInstantiation() {
		Person person = generatePersonWithId();
		LOGGER.debug("Generated Person Instantiated: person = {}", person);
		assertNotNull(person);
		assertNotNull(person.getOtherNames());
	}

}
