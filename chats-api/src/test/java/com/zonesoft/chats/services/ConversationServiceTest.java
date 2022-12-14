package com.zonesoft.chats.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Objects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.zonesoft.chats.data_generators.ConversationRecordBuilder;
//import com.zonesoft.chats.events.PersistenceEventRepository;
import com.zonesoft.chats.models.Conversation;
import com.zonesoft.chats.repositories.ConversationRepository;

import reactor.core.publisher.Flux;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class ConversationServiceTest {
	
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
}
