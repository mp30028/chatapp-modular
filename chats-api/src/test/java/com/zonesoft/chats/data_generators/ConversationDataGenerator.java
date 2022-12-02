package com.zonesoft.chats.data_generators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.jayway.jsonpath.JsonPath;
import com.zonesoft.chats.models.Conversation;
import com.zonesoft.chats.models.Participant;
import com.zonesoft.utils.data_generators.RecordsGeneratorTemplate;

import reactor.util.function.Tuple2;

@SpringBootTest
//@Tag("DataGenerator")
public class ConversationDataGenerator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConversationDataGenerator.class);
	
	private static final String CONVERSATION_COLLECTION_NAME = "conversations";
	@Autowired private ReactiveMongoTemplate mongoTemplate;
	
	private void collectionCreate() {
		mongoTemplate.createCollection(CONVERSATION_COLLECTION_NAME).block();
	}

	private void collectionDrop() {
		mongoTemplate.dropCollection(CONVERSATION_COLLECTION_NAME).block();
	}
	
	private boolean collectionExists() {
		return this.mongoTemplate.collectionExists(CONVERSATION_COLLECTION_NAME).block();
	}
	
	@Disabled
	@Test
	void testCollectionExists() {
		assertNotNull(mongoTemplate);
		assertTrue(collectionExists());
	}
	
	@Disabled
	@Test
	void generateData() throws IOException {
		RecordsGeneratorTemplate<ConversationRecordBuilder, Conversation> generator = new RecordsGeneratorTemplate<>();
		List<Conversation> conversations = generator.generate(()-> new ConversationRecordBuilder());
		LOGGER.debug("ConversationDataGenerator.generateData: conversations={}", conversations);
		List<Participant> fullListOfParticipants = loadParticipants();
		makeConversationsConsistentWithParticipants(conversations, fullListOfParticipants);
	}
	
	private void makeConversationsConsistentWithParticipants(List<Conversation> conversations, List<Participant> fullListOfParticipants) {
		for(Conversation conversation: conversations) {
			
		}
		
	}

	@Test
	void testLoadParticipants() throws IOException {
		loadParticipants();
	}
	
	private List<Participant> loadParticipants() throws IOException{
		Resource resource = new ClassPathResource("__files/persons-fetch-all-response.json");
		String json = resource.getInputStream().toString();
		List<Tuple2<String, String>> parseResult = JsonPath.parse(json).read("$[*]['id','moniker']");
		ParticipantRecordBuilder participantBuilder = new ParticipantRecordBuilder();
		List<Participant> participants = new ArrayList<>();
		for(Tuple2<String, String> person: parseResult) {
			participants.add(participantBuilder.id(person.getT1()).moniker(person.getT2()).build());
		}
		return participants;
	}
}
