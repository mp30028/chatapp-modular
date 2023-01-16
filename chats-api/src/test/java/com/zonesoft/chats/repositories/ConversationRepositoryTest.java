package com.zonesoft.chats.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.zonesoft.chats.data_generators.ConversationRecordBuilder;
import com.zonesoft.chats.models.Conversation;
import com.zonesoft.chats.models.Participant;
import com.zonesoft.utils.data_generators.RecordsGeneratorTemplate;

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
class ConversationRepositoryTest{
	private static final Logger LOGGER = LoggerFactory.getLogger(ConversationRepositoryTest.class);
	private static final String IMAGE_NAME = "mongo:6.0.2";	
	static final MongoDBContainer MONGODB_CONTAINER;
	
	@Autowired ConversationRepository repository;
	
	static {
		LOGGER.debug("From MONGODB_CONTAINER initialisation");
		MONGODB_CONTAINER = new MongoDBContainer(DockerImageName.parse(IMAGE_NAME));
		MONGODB_CONTAINER.start();
	}
	
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {		
		registry.add("spring.data.mongodb.uri", MONGODB_CONTAINER::getReplicaSetUrl);
	}

	@Test
	void simpleTest() throws InterruptedException{
		Integer countAtStart = repository.count().block().intValue();
		Supplier<ConversationRecordBuilder> supplier = (()-> new ConversationRecordBuilder().withDefaults());
		List<Conversation> conversations = new RecordsGeneratorTemplate<ConversationRecordBuilder,Conversation>().generate(supplier);
		Integer resultsSize =
			repository
				.saveAll(conversations)
				.collectList()
				.map(l -> {LOGGER.debug("ConversationRepositoryTest.simpleTest Instantiated-conversations = {}", l); return l;})
				.map(l -> l.size())
				.block();
		LOGGER.debug("ConversationRepositoryTest.simpleTest: Insert results(number-of-conversations).size()={}", resultsSize);
		assertEquals(conversations.size(), resultsSize);
		Integer count = repository.count().block().intValue();
		LOGGER.debug("ConversationRepositoryTest.simpleTest: Querying for count(number-of-conversations)={}", count);
		assertEquals(countAtStart + conversations.size(), count);
	}
	
	@Test
	void findByParticipantPersonIdTest() {
		Integer countAtStart = repository.count().block().intValue();
		Supplier<ConversationRecordBuilder> supplier = (()-> new ConversationRecordBuilder().withDefaults());
		List<Conversation> conversations = new RecordsGeneratorTemplate<ConversationRecordBuilder,Conversation>().generate(supplier);
		repository
			.saveAll(conversations)
			.collectList()
			.map(l -> {LOGGER.debug("ConversationRepositoryTest.findByMonikerTest Instantiated-conversations = {}", l); return l;})
			.block();
		Conversation firstConversation = conversations.get(0);
		Participant firstParticipant = firstConversation.participants().get(0);
		String participantPersonIdToSearch = firstParticipant.getPersonId();
		LOGGER.debug("participantPersonIdToSearch={}",participantPersonIdToSearch);
		repository
			.findByParticipantPersonId(participantPersonIdToSearch)
			.collectList()
			.map(l -> {LOGGER.debug("ConversationRepositoryTest.findByParticipantPersonId found-conversations = {}", l); return l;})
			.block();
	}
}
