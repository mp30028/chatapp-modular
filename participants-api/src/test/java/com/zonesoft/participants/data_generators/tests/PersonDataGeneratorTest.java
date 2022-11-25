package com.zonesoft.participants.data_generators.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zonesoft.participants.data_generators.PersonDataGenerator;
import com.zonesoft.participants.models.Person;

class PersonDataGeneratorTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonDataGenerator.class);
	
	
	@Test
	void test() {
		PersonDataGenerator builder = new PersonDataGenerator();
		Person person = builder.firstname().lastname().id().moniker().generate();
		LOGGER.debug("FROM PersonDataGeneratorTest: person = {}", person);
	}

}
