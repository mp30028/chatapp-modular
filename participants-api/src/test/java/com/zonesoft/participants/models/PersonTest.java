package com.zonesoft.participants.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zonesoft.participants.data_generators.PersonDataGenerator;

class PersonTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonTest.class);
	
	@Test
	void testInstantiation() {
		PersonDataGenerator generator = new PersonDataGenerator();
		Person person = generator.id().moniker().firstname().lastname().otherNames(2,4).generate();
		LOGGER.debug("Generated Person Instantiated: person = {}", person);
		assertNotNull(person);
		assertNotNull(person.getId());
		assertNotNull(person.otherNames());
		for(OtherName otherName: person.otherNames()) {
			assertNotNull(otherName);
			assertNotNull(otherName.getId());
		}
	}

}
