package com.zonesoft.chats.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.zonesoft.chats.configurations.PersonsApiClientConfigurations;
import static com.github.tomakehurst.wiremock.client.WireMock.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersonsApiClientConfigurations.class, PersonService.class})
@TestPropertySource(value="classpath:application.properties")
@AutoConfigureWireMock(port = 9999)

class PersonServiceWiremockTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceWiremockTest.class);
	@Autowired PersonService service;

	@Test
	void testFetchByMoniker_withInlineBodyContent() {
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
		stubFor(
			get(urlEqualTo("/api/persons"))
			.willReturn(
				aResponse()
				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.withBody(bodyToReturn)				
			)
		);
		String result = this.service.testMeOut();
		assertTrue(result.equals(expectedResult));
		LOGGER.debug("testFetchByMoniker_withInlineBodyContent: result = {}", result);
	}


	@Test
	void testFetchByMoniker_withBodyContentFromFile() {
		assertNotNull(service);
		String expectedResult = "[b575baf9-8430-4c58-a361-04ad17bac353, 62616753-4d06-499e-8816-4ccdead06533]";
		stubFor(
			get(urlEqualTo("/api/persons"))
			.willReturn(
				aResponse()
				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.withBodyFile("fetch-response.json")				
			)
		);
		String result = this.service.testMeOut();
		assertTrue(result.equals(expectedResult));
		LOGGER.debug("testFetchByMoniker_withBodyContentFromFile: result = {}", result);
	}

}
