package com.zonesoft.chats.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.zonesoft.chats.data_generators.ConversationRecordBuilder;
import com.zonesoft.chats.data_generators.ParticipantRecordBuilder;
import com.zonesoft.chats.models.Conversation;
import com.zonesoft.chats.models.Participant;
import com.zonesoft.chats.repositories.ConversationRepository;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class ConversationServiceTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConversationServiceTest.class);
	private ReactiveMongoTemplate mockReactiveTemplate;
	
	private PersonService mockPersonService;
	private ConversationRepository mockRepository;
//	private PersistenceEventRepository mockEventsRepository;
	
	private ConversationService service;
	
	@BeforeEach
	void setupBeforeEach() {
		this.mockPersonService = mock(PersonService.class);
		this.mockRepository = mock(ConversationRepository.class);
//		this.mockEventsRepository = mock(PersistenceEventRepository.class);
		this.mockReactiveTemplate = mock(ReactiveMongoTemplate.class);
		this.service = new ConversationService(mockRepository, mockPersonService, mockReactiveTemplate); 
	}
	
	@AfterEach
	void cleanupAfterEach() {
		this.service= null;
		this.mockPersonService=null;
		this.mockRepository=null;
	}
	
	@Test
	void testSetup() {
		assertNotNull(this.service);
	}
	
	@Test
	void testFindAll_GivenNoData_ReturnsEmpty() {
		when(mockRepository.findAll()).thenReturn(Flux.empty());
		 Flux<Conversation> conversationFlux = this.service.findAll();
		 assertEquals(Flux.empty(), conversationFlux);
		 boolean isNullReturned = conversationFlux.all(c -> Objects.isNull(c)).block();
		 assertTrue(isNullReturned);
	}
	
	
	@Test
	void testFindAll_GivenASingleConversation_ReturnsFluxWithSingleConversation() {
		Conversation conversation = new ConversationRecordBuilder().withDefaults().build();
		Flux<Conversation> conversationFlux = Flux.just(conversation);
		when(mockRepository.findAll()).thenReturn(conversationFlux);
		 Flux<Conversation> resultFlux = this.service.findAll();
		 assertEquals(conversationFlux,resultFlux);
	}
	
	@Test
	void testFindByMoniker_GivenASingleConversationWithTestMoniker_ReturnsFluxWithSingleConversation() {
		String testPersonId = "TEST-PERSON-ID-0001";
		String testMoniker = "GUINEA-PIG";
		int NUMBER_OF_OTHER_PARTICIPANTS = 4;

		List<Participant> participants = new ArrayList<>();
		
		//Add the participant to be searched for.
		participants.add(
				new ParticipantRecordBuilder()
				.personId(testPersonId)
				.participationStart(OffsetDateTime.now(ZoneId.of("Z")))
				.build()
		);
		
		//Add a few other participants
		//Note: start with j=1, as first participant already added
		for (int j=1; j < NUMBER_OF_OTHER_PARTICIPANTS; j++ ) {
			participants.add(
					new ParticipantRecordBuilder()
					.withDefaults()
					.build()
			);
		}
		Conversation conversation = new ConversationRecordBuilder().participants(participants).withDefaults().build();
		Flux<Conversation> conversationFlux = Flux.just(conversation);
		when(mockPersonService.fetchPersonIdByMoniker(testMoniker)).thenReturn(Mono.just(testPersonId));
		when(mockRepository.findByParticipantPersonId(testPersonId)).thenReturn(conversationFlux);
		 List<Conversation> findResult = this.service.findByMoniker(testMoniker).collectList().block();
		 assertEquals(1, findResult.size());
		 LOGGER.debug("findResult.get(0).getTitle()={}",findResult.get(0).getTitle());
		 assertEquals(conversation, findResult.get(0));
	}
}
