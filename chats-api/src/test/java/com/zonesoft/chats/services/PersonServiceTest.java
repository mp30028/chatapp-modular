package com.zonesoft.chats.services;


import static org.junit.Assert.assertNotNull;
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
import com.zonesoft.chats.services.clients.PersonsApiClientBuilder;

import reactor.util.function.Tuple2;

import com.github.tomakehurst.wiremock.client.WireMock;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersonsApiClientBuilder.class, PersonService.class })
@TestPropertySource(value = "classpath:application-test.properties")
class PersonServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceTest.class);

	private PersonService service;

	private static final WireMockServer WIREMOCK_SERVER = new WireMockServer(wireMockConfig().dynamicPort());

	@Autowired
	private PersonsApiClientBuilder webClientConfigs;

	@BeforeAll
	static void beforeAll() {
		WIREMOCK_SERVER.start();
	}

	@AfterAll
	static void afterAll() {
		WIREMOCK_SERVER.stop();
	}
	
	@BeforeEach
	void beforeEach() {
		String portString = String.valueOf(WIREMOCK_SERVER.port());
		LOGGER.debug("portString={}", portString);
		this.webClientConfigs.port(portString).domain("localhost").protocol("http").clientName("PersonServiceTest")
				.clientType("UnitTestingClient").reset();
		this.service = new PersonService(webClientConfigs);		
	}

	
	@Test
	void testFetchAll() {
		assertNotNull(this.service);
		WIREMOCK_SERVER.stubFor(WireMock.get(WireMock.urlEqualTo("/api/persons"))
				.willReturn(WireMock.aResponse().withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.withBodyFile("persons-fetch-all-response.json")));
		List<Tuple2<String, String>> fetchResult = this.service.fetchAll().block();
		LOGGER.debug("testFetchAll: result = {}, size={}", fetchResult,fetchResult.size());
	}


	@Test
	void testFetchByMoniker() {
		String moniker = "Cous";
		assertNotNull(this.service);
//		WIREMOCK_SERVER.stubFor(
//				(WireMock.get(WireMock.urlEqualTo("/api/persons"))
//				.withQueryParam("moniker",  WireMock.equalTo(moniker))
//			)
// Above code does not match. Looks like the query parameters get formatted as json. Probably need to change
// the content type text
		WIREMOCK_SERVER.stubFor(WireMock.get(WireMock.urlEqualTo("/api/persons?moniker=" + moniker))
				.willReturn(WireMock.aResponse().withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.withBodyFile("persons-fetch-by-moniker-response.json")));
		List<Tuple2<String, String>> fetchResult = this.service.fetchByMoniker(moniker).block();
		LOGGER.debug("testFetchByMoniker: result = {}, size={}", fetchResult,fetchResult.size());
	}
}
