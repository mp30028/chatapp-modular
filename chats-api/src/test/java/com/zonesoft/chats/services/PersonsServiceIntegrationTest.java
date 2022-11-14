package com.zonesoft.chats.services;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.zonesoft.chats.configurations.PersonsApiClientConfigurations;

import reactor.core.publisher.Flux;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = PersonsApiClientConfigurations.class)
@TestPropertySource(value="classpath:application.properties")
public class PersonsServiceIntegrationTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonsServiceIntegrationTest.class);
	
	
	@Autowired private PersonsApiClientConfigurations configs;
	
	@Test
	void test_get_persons_returnsOK_withPersonsList() {
		Flux<Person> response = configs.getApiClient()
			.get()
			.uri(uriBuilder -> uriBuilder.path(configs.getPath()).build())
			.header("Content-Type", "application/json")
			.retrieve()
			.bodyToFlux(Person.class);
		 List<Person> list = response.collectList().block();//r -> 
		 LOGGER.debug("test_get_persons_returnsOK_withPersonsList: response-content = {}",list);
	}
	
}

