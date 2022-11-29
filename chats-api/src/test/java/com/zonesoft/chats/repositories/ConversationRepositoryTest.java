package com.zonesoft.chats.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import com.zonesoft.chats.data_generators.ConversationRecordBuilder;
import com.zonesoft.chats.models.Conversation;
import com.zonesoft.utils.data_generators.RecordsGeneratorTemplate;

@SpringBootTest
//@Testcontainers
//@DataMongoTest(excludeAutoConfiguration = { MongoAutoConfiguration.class})
//@ContextConfiguration(classes = {DateToOffsetDateTime.class, OffsetDateTimeToDate.class})
class ConversationRepositoryTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConversationRepositoryTest.class);
	
	private static final String IMAGE_NAME = "mongo:6.0.2";

	 @Container private static final MongoDBContainer MONGODB_CONTAINER;
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
	void simpleTest() throws InterruptedException {
		Supplier<ConversationRecordBuilder> supplier = (()-> new ConversationRecordBuilder());
		List<Conversation> conversations = new RecordsGeneratorTemplate<ConversationRecordBuilder,Conversation>().id(true).generate(supplier);
		repository
		.saveAll(conversations)
		.subscribe(c -> LOGGER.debug("ConversationRepositoryTest.simpleTest Instantiated-message = {}", c));
		Thread.sleep(1500);
		Long count = repository.count().block();
		assertEquals(conversations.size(), count);
		Thread.sleep(1500);
	}
}
