package com.zonesoft.chats.data_generators;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import com.jayway.jsonpath.JsonPath;
import com.zonesoft.chats.models.Conversation;
import com.zonesoft.chats.models.Participant;
import com.zonesoft.utils.data_generators.RecordsGeneratorTemplate;

import reactor.core.publisher.Mono;

import static com.zonesoft.utils.data_generators.Generator.inputStreamToString;
import static com.zonesoft.utils.data_generators.Generator.randomSubset;

@SpringBootTest
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
	
	private List<Conversation> collectionInsert(List<Conversation> conversations) {
		return this.mongoTemplate.insertAll(Mono.just(conversations),Conversation.class).collectList().block();
	}

	
	@Test
	void testCollectionExists() {
		assertNotNull(mongoTemplate);
		assertTrue(collectionExists());
	}
	
	@Test
	@Disabled("Data Generator. Will overwrite data in database. Only enable if sure")
	void generateData() throws IOException {
		List<Participant> participants = loadParticipants();
		RecordsGeneratorTemplate<ConversationRecordBuilder, Conversation> generator = new RecordsGeneratorTemplate<>();
		List<Conversation> conversations = 
				generator.generate(()-> 
					(new ConversationRecordBuilder())
					.participants(randomSubset(participants ,2, participants.size()))
					.withDefaults(false)
				);
		if (collectionExists()) {
			collectionDrop();
			collectionCreate();
		}
		conversations = collectionInsert(conversations);
		LOGGER.debug("ConversationDataGenerator.generateData: conversations={}", conversations);
	}
	
	@Test
	void testLoadParticipants() throws IOException {
		loadParticipants();
	}
	
	private List<Participant> loadParticipants(){
		Resource resource = new ClassPathResource("__files/persons-fetch-all-response.json");
		String json = null;
		try {
			json = inputStreamToString(resource.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Map<String, String>> resultList = JsonPath.parse(json).read("$[*]['id','moniker']");
		List<Participant> participants = new ArrayList<Participant>();
		for(Map<String, String> result: resultList) {
			Participant participant = (new ParticipantRecordBuilder())
//					.id(result.get("id"))
					.moniker(result.get("moniker"))
					.withDefaults()
					.build();
			participants.add(participant);
		}
		return participants;
	}
	

}