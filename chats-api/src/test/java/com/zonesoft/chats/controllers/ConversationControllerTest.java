package com.zonesoft.chats.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.zonesoft.chats.data_generators.ConversationRecordBuilder;
import com.zonesoft.chats.models.Conversation;
import com.zonesoft.chats.services.ConversationService;
import com.zonesoft.utils.data_generators.RecordsGeneratorTemplate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class ConversationControllerTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConversationControllerTest.class);
	private ConversationService mockedService;
	private WebTestClient client;
	
	@BeforeEach
	void setUpBeforeEach() {
		mockedService = mock(ConversationService.class);
		ConversationController controller = new ConversationController(mockedService);
		client = WebTestClient.bindToController(controller).build();
		LOGGER.debug("------------------ Setup up done  --------------");
	}
	
	@AfterEach
	void cleanUpAfterEach() {
		client = null;
		mockedService = null;
		LOGGER.debug("------------------ Clean up completed  --------------");
	}
	
	@Test
	@Order(1)
	void testInitialisation() {
		assertNotNull(mockedService);
		assertNotNull(client);
		LOGGER.debug("Initialisation completed. <ConversationService>mockedService-state={}, <WebTestClient>client-state={}", mockedService,client);
	}
	
	@Test
	void testFindAll_whenServiceReturnsEmpty_thenReturns204NoContent() {
		when(mockedService.findAll()).thenReturn(Flux.empty());
		LOGGER.debug("FROM: testFindAll_whenServiceReturnsEmpty_thenReturns204NoContent");
		client
			.get()
			.uri(uriBuilder -> uriBuilder.path("/api/conversations").build())
			.exchange()
			.expectStatus()
			.isNoContent();
	}

	
	@Test
	void testFindAll_whenServiceReturnsSingleConversation_thenReturns200Ok() {
		Conversation conversation = new ConversationRecordBuilder().withDefaults().build();
		LOGGER.debug("testFindAll_whenServiceReturnsSingleConversation_thenReturns200Ok: built-conversation={}", conversation);
		when(mockedService.findAll()).thenReturn(Flux.just(conversation));
		LOGGER.debug("FROM: testFindAll_whenServiceReturnsSingleConversation_thenReturns200Ok");
		client
			.get()
			.uri(uriBuilder -> uriBuilder.path("/api/conversations").build())
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.length()").isEqualTo(1)
			.jsonPath("$[0].id").isEqualTo(conversation.getId())
			.jsonPath("$[0].participants.length()").isEqualTo(conversation.participants().size())
			.jsonPath("$[0].messages.length()").isEqualTo(conversation.messages().size())
			.consumeWith(r -> LOGGER.debug("testFindAll_whenServiceReturnsSingleConversation_thenReturns200Ok: response = {}",new String(r.getResponseBody(), StandardCharsets.UTF_8)));
	}
	
	@Test
	void testFindAll_whenServiceReturnsMultipleConversations_thenReturns200Ok() {
		Supplier<ConversationRecordBuilder> supplier = () -> new ConversationRecordBuilder().withDefaults();
		RecordsGeneratorTemplate<ConversationRecordBuilder, Conversation> generator = new RecordsGeneratorTemplate<>();
		List<Conversation> conversations = generator.generate(supplier);
		LOGGER.debug("testFindAll_whenServiceReturnsMultipleConversations_thenReturns200Ok: built-conversations={}", conversations);
		when(mockedService.findAll()).thenReturn(Flux.fromIterable(conversations));
		LOGGER.debug("FROM: testFindAll_whenServiceReturnsMultipleConversations_thenReturns200Ok");
		client
			.get()
			.uri(uriBuilder -> uriBuilder.path("/api/conversations").build())
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.length()").isEqualTo(conversations.size())
			.jsonPath("$[0].id").isEqualTo(conversations.get(0).getId())
			.jsonPath("$[0].participants.length()").isEqualTo(conversations.get(0).participants().size())
			.jsonPath("$[0].messages.length()").isEqualTo(conversations.get(0).messages().size())
			.consumeWith(r -> LOGGER.debug("testFindAll_whenServiceReturnsMultipleConversations_thenReturns200Ok: response = {}",new String(r.getResponseBody(), StandardCharsets.UTF_8)));
	}
	
	@Test
	void testFindById_whenServiceReturnsEmpty_thenReturns204NoContent() {
		String id = "13409dfkj309fj";
		when(mockedService.findById(id)).thenReturn(Mono.empty());
		client
			.get()
			.uri(uriBuilder -> uriBuilder.path("/api/conversations/{id}").build(id))
			.exchange()
			.expectStatus()
			.isNoContent()
			.expectBody()
			.consumeWith(r -> LOGGER.debug("testFindById_whenServiceReturnsEmpty_thenReturns204NoContent: responseBody-is-null = {}",Objects.isNull(r.getResponseBody())));
	}
	
	@Test
	void testFindById_whenServiceReturnsARecord_thenReturns200Ok() {
		Conversation conversation =  new ConversationRecordBuilder().withDefaults().build();
		String id = conversation.getId();
		LOGGER.debug("testFindById_whenServiceReturnsARecord_thenReturns200Ok: built-conversations={}", conversation);
		when(mockedService.findById(id)).thenReturn(Mono.just(conversation));
		client
			.get()
			.uri(uriBuilder -> uriBuilder.path("/api/conversations/{id}").build(id))
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.id").isEqualTo(conversation.getId())
			.jsonPath("$.participants.length()").isEqualTo(conversation.participants().size())
			.jsonPath("$.messages.length()").isEqualTo(conversation.messages().size())
			.consumeWith(r -> LOGGER.debug("testFindById_whenServiceReturnsARecord_thenReturns200Ok: response = {}",new String(r.getResponseBody(), StandardCharsets.UTF_8)));
	}
}
