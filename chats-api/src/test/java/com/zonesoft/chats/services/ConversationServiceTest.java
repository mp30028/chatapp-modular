package com.zonesoft.chats.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Objects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.zonesoft.chats.models.Conversation;
import com.zonesoft.chats.repositories.ConversationRepository;

import reactor.core.publisher.Flux;

@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = { PersonsApiClientBuilder.class, PersonService.class })
@TestPropertySource(value = "classpath:application.properties")
class ConversationServiceTest {
	
	private PersonService mockPersonService;
	private ConversationRepository mockRepository;
	private ConversationService service;
	
	@BeforeEach
	void setupBeforeEach() {
		this.mockPersonService = mock(PersonService.class);
		this.mockRepository = mock(ConversationRepository.class);
		this.service = new ConversationService(mockRepository, mockPersonService); 
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

}
