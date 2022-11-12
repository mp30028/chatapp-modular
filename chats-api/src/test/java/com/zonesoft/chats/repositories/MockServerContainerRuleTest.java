package com.zonesoft.chats.repositories;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockserver.model.HttpRequest.request;
//import static org.mockserver.model.HttpResponse.response;
import static org.mockito.Mockito.mock;

import java.nio.charset.StandardCharsets;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
//import org.mockserver.client.MockServerClient;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.utility.DockerImageName;

import com.zonesoft.persons.configurations.RouterConfiguration;
import com.zonesoft.persons.handlers.PersonHandler;
import com.zonesoft.persons.services.PersonService;

public class MockServerContainerRuleTest {

    static final DockerImageName MOCKSERVER_IMAGE = DockerImageName.parse(
        "mockserver/mockserver:mockserver-5.14.0"
    );
    
    MockServerContainer mockServer = new MockServerContainer(MOCKSERVER_IMAGE);
//    WebTestClient client = new WebTestClient();

//    // creatingProxy {
//    @Rule
//    public MockServerContainer mockServer = new MockServerContainer(
//        MOCKSERVER_IMAGE.withTag("mockserver-" + MockServerClient.class.getPackage().getImplementationVersion())
//    );

    // }
    private WebTestClient client;
    
	@BeforeEach
	void setUpBeforeEach() {

		client = WebTestClient.bindToServer().baseUrl("http://localhost:9999").build();
				
				//.bindToRouterFunction(routes).build();
	}
	
    @Test
    public void shouldReturnExpectation() throws Exception {
		client
		.get()
		.uri(uriBuilder -> uriBuilder.path("/persons").build())
		.exchange()
		.expectStatus()
		.isOk()
		.expectBody()
		.jsonPath("$[0].id").isEqualTo(PERSON_1.getId())
		.jsonPath("$.length()").isEqualTo(PERSONS.size())
		.consumeWith(r -> LOGGER.debug("get_persons: response = {}",new String(r.getResponseBody(), StandardCharsets.UTF_8)));
    	
	
    }
}
