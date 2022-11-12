package com.zonesoft.chats.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.reactive.function.client.WebClient;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.zonesoft.chats.models.Conversation;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Testcontainers()
@DataMongoTest
class ConversationRepositoryTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConversationRepositoryTest.class);
	
	private static final String IMAGE_NAME = "mongo:6.0.2";

    private static final MongoDBContainer MONGODB_CONTAINER;
    static {
    	LOGGER.debug("From MONGODB_CONTAINER initialisation");
    	MONGODB_CONTAINER = new MongoDBContainer(DockerImageName.parse(IMAGE_NAME));
        MONGODB_CONTAINER.start();
    }

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {		
		registry.add("spring.data.mongodb.uri", MONGODB_CONTAINER::getReplicaSetUrl);  
	}
	
	@Autowired ConversationRepository repository;
	
	private Flux<String> fetchParticipantPersonIds(){
		WebClient client = WebClient.builder()
				  .baseUrl("http://localhost:8080")
				  .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
				  .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
				  .build();
		client.get().exchangeToFlux(null);
		return null;
	}
	
	private Mono<Conversation> createAndInsertSingleConversation() {
		Conversation conversation = new Conversation();
		conversation.getParticipants().add(null);
		Mono<Conversation> createdConversation = repository.insert(conversation);
//		generateNickname(), generateFirstName(generateGender()), generateLastName());
//		Mono<Person> createdPerson =  personRepository.insert(person);
		return createdConversation;
	}
	
//	private List<Person>  createAndInsertPersons() {
//		final int MAX_PERSONS = 7;
//		final int MIN_PERSONS = 2;
//		int numberOfPersons = generateRandomInt(MIN_PERSONS, MAX_PERSONS);
//		List<Person> createdPersons = new ArrayList<>();
//		for (int j=0; j < numberOfPersons; j++) {
//			Person createdPerson = createAndInsertSinglePerson().block(); // Wait until person is created
//			createdPersons.add(createdPerson);
//		}
//		return createdPersons;
//	}
}
