package com.zonesoft.persons.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class PersonTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonTest.class);
	

	@Test
	void testPersonWithValidAttributes() {
		String ID = "ABCDEFG";
		String MONIKER = "_TEST_";
		String LASTNAME = "Smith";
		String FIRSTNAME = "John";
		Person sut = new Person(ID,MONIKER,FIRSTNAME,LASTNAME);		
		
		assertEquals(ID, sut.getId());
		assertEquals(MONIKER, sut.getMoniker());
		assertEquals(LASTNAME, sut.getLastname());
		assertEquals(FIRSTNAME, sut.getFirstname());
		LOGGER.debug(sut.toString());
	}

	@Test
	void testPersonWithNullId() {
		String ID = null;
		String MONIKER = "_TEST_";
		String LASTNAME = "Smith";
		String FIRSTNAME = "John";
		Person sut = new Person(ID,MONIKER,FIRSTNAME,LASTNAME);		
		
		assertEquals(ID, sut.getId());
		assertEquals(MONIKER, sut.getMoniker());
		assertEquals(LASTNAME, sut.getLastname());
		assertEquals(FIRSTNAME, sut.getFirstname());
		LOGGER.debug(sut.toString());
	}

	@Test
	void testPersonWithEmptyId() {
		String ID = "";
		String MONIKER = "_TEST_";
		String LASTNAME = "Smith";
		String FIRSTNAME = "John";
		Person sut = new Person(ID,MONIKER,FIRSTNAME,LASTNAME);		
		
		assertEquals(ID, sut.getId());
		assertEquals(MONIKER, sut.getMoniker());
		assertEquals(LASTNAME, sut.getLastname());
		assertEquals(FIRSTNAME, sut.getFirstname());
		LOGGER.debug(sut.toString());
	}
}
