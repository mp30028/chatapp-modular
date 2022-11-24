package com.zonesoft.chats.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = PersonsApiClientConfigurations.class)
@TestPropertySource(value="classpath:application.properties")
class PersonServiceWiremockTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceWiremockTest.class);
    private static final WireMockServer WIREMOCK_SERVER = new WireMockServer(wireMockConfig().dynamicPort());
	
	@Autowired private PersonsApiClientConfigurations webClientConfigs;
	
	private PersonService service;	
	

    @BeforeEach
    public void beforeEachTest() {
    	webClientConfigs.setPort(WIREMOCK_SERVER.port());
    	webClientConfigs.setDomain("localhost");
    	webClientConfigs.setProtocol("http");
        this.service = new PersonService(webClientConfigs);
    }
	
    @BeforeAll
    public static void beforeAll() {
        WIREMOCK_SERVER.start();
    }

    @AfterAll
    public static void afterAll() {
        WIREMOCK_SERVER.stop();
    }
    
	@Test
	void testFetchAll_withMockedInlineBodyContent() {
		assertNotNull(service);
		String bodyToReturn = "[\r\n"
				+ "    {\r\n"
				+ "    \"firstname\": \"Gillian\",\r\n"
				+ "    \"id\": \"b575baf9-8430-4c58-a361-04ad17bac353\",\r\n"
				+ "    \"lastname\": \"WILSON\",\r\n"
				+ "    \"moniker\": \"Scara\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "    \"firstname\": \"Craig\",\r\n"
				+ "    \"id\": \"62616753-4d06-499e-8816-4ccdead06533\",\r\n"
				+ "    \"lastname\": \"PATERSON\",\r\n"
				+ "    \"moniker\": \"Nosey\"\r\n"
				+ "    }\r\n"
				+ "]";
		String expectedResult = "[b575baf9-8430-4c58-a361-04ad17bac353, 62616753-4d06-499e-8816-4ccdead06533]";
		WIREMOCK_SERVER.stubFor(
			get(urlEqualTo("/api/persons"))
			.willReturn(
				aResponse()
				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.withBody(bodyToReturn)				
			)
		);
		String result = this.service.fetchAll().collectList().block().toString();
		assertTrue(result.equals(expectedResult));
		LOGGER.debug("testFetchAll_withMockedInlineBodyContent: result = {}", result);
	}


	@Test
	void testFetchAll_withMockedBodyContentFromFile() {
		assertNotNull(service);
		String expectedResult = "[b575baf9-8430-4c58-a361-04ad17bac353, 62616753-4d06-499e-8816-4ccdead06533]";
		WIREMOCK_SERVER.stubFor(
			get(urlEqualTo("/api/persons"))
			.willReturn(
				aResponse()
				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.withBodyFile("fetch-response.json")				
			)
		);
		String result = this.service.fetchAll().collectList().block().toString();
		assertTrue(result.equals(expectedResult));
		LOGGER.debug("testFetchAll_withMockedBodyContentFromFile: result = {}", result);
	}

}
