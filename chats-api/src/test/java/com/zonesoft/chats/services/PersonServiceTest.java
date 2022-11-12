package com.zonesoft.chats.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.zonesoft.chats.configurations.PersonsApiClientConfigurations;

import reactor.core.publisher.Flux;
import static com.zonesoft.utils.data_generators.Generator.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersonsApiClientConfigurations.class, PersonService.class})
@TestPropertySource(value="classpath:application.properties")
class PersonServiceTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceTest.class);
	
	private PersonService service;
	
	private static final int MIN_PERSONS = 2;
	private static final int MAX_PERSONS = 10;
	private static Person PERSON_1;
	private static Person PERSON_2;
	private static List<Person> PERSONS;

	private static void createTestData() {
		PERSON_1 = new Person(generateUUID(), generateNickname(), generateFirstName(generateGender()),generateLastName());
		PERSON_2 = new Person(generateUUID(), generateNickname(), generateFirstName(generateGender()),generateLastName());
		PERSONS = new ArrayList<Person>();
		PERSONS.add(PERSON_1);
		PERSONS.add(PERSON_2);
		int noOfPersons = generateRandomInt(MIN_PERSONS, MAX_PERSONS);
		for (int j = MIN_PERSONS; j < noOfPersons; j++) {
			PERSONS.add(new Person(generateUUID(), generateNickname(), generateFirstName(generateGender()),
					generateLastName()));
		}
	}
	
	private int selectARandomPersonsIndex() {
		return generateRandomInt(0, PERSONS.size()-1);
	}

	@BeforeAll
	static void setUpBeforeAll() {
		createTestData();
	}
	
	@BeforeEach
	void setupBeforeEach() {
		service = mock(PersonService.class);
	}
	
	@Test
	void testFetchAll() {
		assertNotNull(service);
		when(service.fetchAll()).thenReturn(Flux.fromIterable(PERSONS).map(p -> p.getId()));
		Flux<String> result = service.fetchAll();
		List<String> list = result.collectList().block();
		assertEquals(PERSONS.size(), list.size());
		for (int j=0; j<list.size(); j++) {
			assertEquals(PERSONS.get(j).getId(), list.get(j));
		}
		LOGGER.debug("testFetchAll: result={}", list);
	}

	@Test
	void testFetchByMoniker() {
		assertNotNull(service);
		int selectedIndex = selectARandomPersonsIndex();
		String moniker = PERSONS.get(selectedIndex).getMoniker();
		List<String> filteredList =  PERSONS
										.stream()
										.filter(p -> p.getMoniker().equals(moniker))
										.map(p-> p.getId())
										.toList();
		when(service.fetchByMoniker(moniker)).thenReturn(Flux.fromIterable(filteredList));
		List<String> fetchedList =  service
										.fetchByMoniker(moniker)
										.collectList()
										.block();
		assertTrue(fetchedList.size() > 0);
		assertEquals(filteredList.size(), fetchedList.size());
		for (int j=0; j<fetchedList.size(); j++) {
			assertEquals(filteredList.get(j), fetchedList.get(j));
		}
		LOGGER.debug("testFetchByMoniker: moniker={}, result={}",moniker, fetchedList);
	}
}
