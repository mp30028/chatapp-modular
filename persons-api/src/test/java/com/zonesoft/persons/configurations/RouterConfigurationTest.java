package com.zonesoft.persons.configurations;


//import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;

import com.zonesoft.persons.handlers.PersonHandler;
import com.zonesoft.persons.models.Person;
import com.zonesoft.persons.services.PersonService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.zonesoft.utils.data_generators.Generator.*;

class RouterConfigurationTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RouterConfigurationTest.class);
	
	private static final int MIN_PERSONS = 2;
	private static final int MAX_PERSONS = 10;
	private PersonService service;
	private WebTestClient client;
	private static Person PERSON_1;
	private static Person PERSON_2;
	private static List<Person> PERSONS;

	private static void createTestData() {
		PERSON_1 = new Person(generateUUID(), generateNickname(), generateFirstName(generateGender()),
				generateLastName());
		PERSON_2 = new Person(generateUUID(), generateNickname(), generateFirstName(generateGender()),
				generateLastName());
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

	@AfterAll
	static void cleanUpAfterAll() {
		PERSONS = null;
		PERSON_1 = null;
		PERSON_2 = null;
	}

	@BeforeEach
	void setUpBeforeEach() {
		service = mock(PersonService.class);
		PersonHandler handler = new PersonHandler(service);
		RouterFunction<?> routes = new RouterConfiguration().routes(handler);
		client = WebTestClient.bindToRouterFunction(routes).build();
	}

	@Test
	void test_get_persons_returnsOK_withPersonsList() {
		when(service.findAll()).thenReturn(Flux.fromIterable(PERSONS));
		client
			.get()
			.uri(uriBuilder -> uriBuilder.path("/api/persons").build())
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$[0].id").isEqualTo(PERSON_1.getId())
			.jsonPath("$.length()").isEqualTo(PERSONS.size())
			.consumeWith(r -> LOGGER.debug("get_persons: response = {}",new String(r.getResponseBody(), StandardCharsets.UTF_8)));
	}
	
	@Test
	void test_get_personById_returnsOK_withPerson() {
		int selectedPersonIndex = selectARandomPersonsIndex();
		Person selectedPerson = PERSONS.get(selectedPersonIndex);
		LOGGER.debug("selectedPerson = {}", selectedPerson);
		when(service.findById(selectedPerson.getId())).thenReturn(Mono.just(selectedPerson));
		client
			.get()
			.uri(uriBuilder -> uriBuilder.path("/api/persons/{id}").build(selectedPerson.getId()))
			.header("Content-Type", "application/json")
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.id")
			.isEqualTo(selectedPerson.getId())
			.consumeWith(r -> LOGGER.debug("get_personById: response = {}",new String(r.getResponseBody(), StandardCharsets.UTF_8)));
	}
	
	@Test
	void test_post_persons_returnsOK_withNewPerson() {
		Person newPerson =  new Person(generateUUID(), generateNickname(), generateFirstName(generateGender()),generateLastName());
		LOGGER.debug("newPerson = {}", newPerson);
		when(service.insert(any())).thenReturn(Mono.just(newPerson));
		client
			.post()
			.uri(uriBuilder -> uriBuilder.path("/api/persons").build())
			.header("Content-Type", "application/json")
			.body(Mono.just(newPerson),Person.class)
			.exchange()
			.expectStatus()
			.isCreated()
			.expectBody()
			.jsonPath("$.id")
			.isEqualTo(newPerson.getId())
			.consumeWith(r -> LOGGER.debug("post_persons: response = {}",new String(r.getResponseBody(), StandardCharsets.UTF_8)));
	}
	
	@Test
	void test_put_person_returnsOK_withUpdatedPerson() {
		int selectedPersonIndex = selectARandomPersonsIndex();
		Person selectedPerson = PERSONS.get(selectedPersonIndex);
		LOGGER.debug("FROM putPerson: selectedPerson = {}", selectedPerson);
		String newMoniker = generateNickname();
		selectedPerson.setMoniker(newMoniker);
		when(service.update(any())).thenReturn(Mono.just(selectedPerson));
		client
			.put()
			.uri(uriBuilder -> uriBuilder.path("/api/persons/{id}").build(selectedPerson.getId()))
			.header("Content-Type", "application/json")
			.body(Mono.just(selectedPerson),Person.class)
			.exchange()
			.expectStatus()
			.isAccepted()
			.expectBody()
			.consumeWith(r -> LOGGER.debug("put_person: response = {}",new String(r.getResponseBody(), StandardCharsets.UTF_8)))
			.jsonPath("$.id").isEqualTo(selectedPerson.getId())
			.jsonPath("$.moniker").isEqualTo(newMoniker);
	}
	
	@Test
	void test_delete_personById_returnsOK() {
		int selectedPersonIndex = selectARandomPersonsIndex();
		Person selectedPerson = PERSONS.get(selectedPersonIndex);
		LOGGER.debug("FROM deletePerson: selectedPerson = {}", selectedPerson);
		Mono<Void> voidReturn  = Mono.empty();
		when(service.deleteById(selectedPerson.getId())).thenReturn(voidReturn);
		client
			.delete()
			.uri(uriBuilder -> uriBuilder.path("/api/persons/{id}").build(selectedPerson.getId()))
			.header("Content-Type", "application/json")
			.exchange()
			.expectStatus()
			.isAccepted();
	}
	
	@Test
	void test_get_personByMoniker_returnsOK_withFoundPersons() {
		int selectedPersonIndex = selectARandomPersonsIndex();
		Person selectedPerson = PERSONS.get(selectedPersonIndex);
		LOGGER.debug("selectedPerson = {}", selectedPerson);
		when(service.findByMoniker(selectedPerson.getMoniker())).thenReturn(Flux.just(selectedPerson));
		client
			.get()
			.uri(uriBuilder -> uriBuilder.path("/api/persons").queryParam("moniker", selectedPerson.getMoniker()).build())
			.header("Content-Type", "application/json")
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			
			.jsonPath("$[0].id").isEqualTo(selectedPerson.getId())
			.jsonPath("$.length()").isEqualTo(1)
			.consumeWith(r -> LOGGER.debug("get_persons: response = {}",new String(r.getResponseBody(), StandardCharsets.UTF_8)));
			
//			.jsonPath("$.id")
//			.isEqualTo(selectedPerson.getId())
//			.consumeWith(r -> LOGGER.debug("test_get_personByMoniker_returnsOK_withFoundPersons: response = {}",new String(r.getResponseBody(), StandardCharsets.UTF_8)));
	}
	
}

