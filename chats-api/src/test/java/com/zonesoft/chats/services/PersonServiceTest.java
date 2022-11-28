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
import com.zonesoft.chats.configurations.PersonsApiClientConfigurations;
import reactor.util.function.Tuple2;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersonsApiClientConfigurations.class, PersonService.class})
@TestPropertySource(value="classpath:application.properties")
class PersonServiceTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceTest.class);
	
	private PersonService mockService;
	
    private static final WireMockServer WIREMOCK_SERVER = new WireMockServer(wireMockConfig().dynamicPort());
	
	@Autowired private PersonsApiClientConfigurations webClientConfigs;
	
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
	
	@Test
	void testFetchAll_withMockedBodyContentFromFile() {
		assertNotNull(mockService);
		WIREMOCK_SERVER.stubFor(
			get(urlEqualTo("/api/persons"))
			.willReturn(
				aResponse()
				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.withBodyFile("persons-fetch-all-response.json")				
			)
		);
		List<Tuple2<String, String> > listOfIds = this.mockService.fetchAll().block();
		LOGGER.debug("testFetchAll_withMockedBodyContentFromFile: result = {}", listOfIds);

	}

}
