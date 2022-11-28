package com.zonesoft.chats.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.zonesoft.chats.configurations.PersonsApiClientConfigurations;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.zonesoft.utils.data_generators.Generator.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersonsApiClientConfigurations.class, PersonService.class})
@TestPropertySource(value="classpath:application.properties")
class PersonServiceTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceTest.class);
	
	private PersonService mockService;
	
    private static final WireMockServer WIREMOCK_SERVER = new WireMockServer(wireMockConfig().dynamicPort());
	
	@Autowired private PersonsApiClientConfigurations webClientConfigs;
	
//	private static final int MIN_PERSONS = 2;
//	private static final int MAX_PERSONS = 10;
//	private static Person PERSON_1;
//	private static Person PERSON_2;
//	private static List<Person> PERSONS;

//	private static void createTestData() {
//		PERSON_1 = new Person(generateUUID(), generateNickname(), generateFirstName(generateGender()),generateLastName());
//		PERSON_2 = new Person(generateUUID(), generateNickname(), generateFirstName(generateGender()),generateLastName());
//		PERSONS = new ArrayList<Person>();
//		PERSONS.add(PERSON_1);
//		PERSONS.add(PERSON_2);
//		int noOfPersons = generateRandomInt(MIN_PERSONS, MAX_PERSONS);
//		for (int j = MIN_PERSONS; j < noOfPersons; j++) {
//			PERSONS.add(new Person(generateUUID(), generateNickname(), generateFirstName(generateGender()),
//					generateLastName()));
//		}
//	}
	
//	private int selectARandomPersonsIndex() {
//		return generateRandomInt(0, PERSONS.size()-1);
//	}

//	@BeforeAll
//	static void setUpBeforeAll() {
//		createTestData();
//	}
	

	
    @BeforeEach
    public void beforeEachTest() {
    	webClientConfigs.setPort(WIREMOCK_SERVER.port());
    	webClientConfigs.setDomain("localhost");
    	webClientConfigs.setProtocol("http");
        this.mockService = new PersonService(webClientConfigs);
    }
	
    @BeforeAll
    public static void beforeAll() {
        WIREMOCK_SERVER.start();
    }

    @AfterAll
    public static void afterAll() {
        WIREMOCK_SERVER.stop();
    }
	
//	@Test
//	void testFetchAll_withMockedBodyContentFromFile() {
//		assertNotNull(mockService);
////		String expectedResult = "[\"30357fee-940e-4cf3-8a26-4a131b9375cf\", \"30357fee-940e-4cf3-8a26-4a131b9375cf\", \"5386ce30-dea3-4a6f-9e01-1ed6e029de07\", \"4ffcaa1f-9e0b-4d21-a4cc-d294ca45a5f6\", \"4ffcaa1f-9e0b-4d21-a4cc-d294ca45a5f6\", \"4ffcaa1f-9e0b-4d21-a4cc-d294ca45a5f6\", \"9977b336-ad14-486f-81c6-876e7f85fdc0\", \"9977b336-ad14-486f-81c6-876e7f85fdc0\", \"9977b336-ad14-486f-81c6-876e7f85fdc0\", \"b756e5a5-2e56-450d-9d52-9548caa1d011\", \"8153e470-6fd8-417f-b546-886f26522e40\", \"8153e470-6fd8-417f-b546-886f26522e40\", \"d9fad6ba-0326-4746-8fdb-7020d3239c56\", \"63836de7c4197472e8a5bccd\"]";
//		WIREMOCK_SERVER.stubFor(
//			get(urlEqualTo("/api/persons"))
//			.willReturn(
//				aResponse()
//				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//				.withBodyFile("persons-fetch-all-response.json")				
//			)
//		);
//		List<String> listOfIds = this.mockService.fetchAll().block();
////		assertTrue(result.equals(expectedResult));
//	
//		LOGGER.debug("testFetchAll_withMockedBodyContentFromFile: result = {}", listOfIds);
//
//	}
    
	@Test
	void testFetchAll_withMockedBodyContentFromFile() {
		assertNotNull(mockService);
//		String expectedResult = "[\"30357fee-940e-4cf3-8a26-4a131b9375cf\", \"30357fee-940e-4cf3-8a26-4a131b9375cf\", \"5386ce30-dea3-4a6f-9e01-1ed6e029de07\", \"4ffcaa1f-9e0b-4d21-a4cc-d294ca45a5f6\", \"4ffcaa1f-9e0b-4d21-a4cc-d294ca45a5f6\", \"4ffcaa1f-9e0b-4d21-a4cc-d294ca45a5f6\", \"9977b336-ad14-486f-81c6-876e7f85fdc0\", \"9977b336-ad14-486f-81c6-876e7f85fdc0\", \"9977b336-ad14-486f-81c6-876e7f85fdc0\", \"b756e5a5-2e56-450d-9d52-9548caa1d011\", \"8153e470-6fd8-417f-b546-886f26522e40\", \"8153e470-6fd8-417f-b546-886f26522e40\", \"d9fad6ba-0326-4746-8fdb-7020d3239c56\", \"63836de7c4197472e8a5bccd\"]";
		WIREMOCK_SERVER.stubFor(
			get(urlEqualTo("/api/persons"))
			.willReturn(
				aResponse()
				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.withBodyFile("persons-fetch-all-response.json")				
			)
		);
		List<Tuple2<String, String> > listOfIds = this.mockService.fetchAll().block();
//		assertTrue(result.equals(expectedResult));
	
		LOGGER.debug("testFetchAll_withMockedBodyContentFromFile: result = {}", listOfIds);

	}
	
//	@Test
//	void testFetchAll() {
//		assertNotNull(mockService);
//		when(mockService.fetchAll()).thenReturn(Flux.fromIterable(PERSONS).map(p -> p.getId()));
//		Flux<String> result = service.fetchAll();
//		List<String> list = result.collectList().block();
//		assertEquals(PERSONS.size(), list.size());
//		for (int j=0; j<list.size(); j++) {
//			assertEquals(PERSONS.get(j).getId(), list.get(j));
//		}
//		LOGGER.debug("testFetchAll: result={}", list);
//	}

//	@Test
//	void testFetchByMoniker() {
//		assertNotNull(service);
//		int selectedIndex = selectARandomPersonsIndex();
//		String moniker = PERSONS.get(selectedIndex).getMoniker();
//		List<String> filteredList =  PERSONS
//										.stream()
//										.filter(p -> p.getMoniker().equals(moniker))
//										.map(p-> p.getId())
//										.toList();
//		when(service.fetchByMoniker(moniker)).thenReturn(Flux.fromIterable(filteredList));
//		List<String> fetchedList =  service
//										.fetchByMoniker(moniker)
//										.collectList()
//										.block();
//		assertTrue(fetchedList.size() > 0);
//		assertEquals(filteredList.size(), fetchedList.size());
//		for (int j=0; j<fetchedList.size(); j++) {
//			assertEquals(filteredList.get(j), fetchedList.get(j));
//		}
//		LOGGER.debug("testFetchByMoniker: moniker={}, result={}",moniker, fetchedList);
//	}
}
