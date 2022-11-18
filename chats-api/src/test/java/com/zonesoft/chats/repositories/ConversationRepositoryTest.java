package com.zonesoft.chats.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.zonesoft.chats.models.Conversation;
import static com.zonesoft.chats.data_generators.ConversationGenerator.*;

//@SpringBootTest
@Testcontainers()
@DataMongoTest
//@ContextConfiguration(classes = {MongoConfig.class})
//@TestPropertySource(value="classpath:application.properties")
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
	
	

	
	@Test
	void simpleTest() {
		Conversation conversation = generateConversation();
		LOGGER.debug("simpleTest: pre-insert conversation={}", conversation );
		Conversation savedConversation = repository.insert(conversation).block();
		Long count = repository.count().block();
		assertEquals(1L, count);
//		LOGGER.debug("simpleTest: post-inset savedConversation={}", savedConversation );
	}
}
