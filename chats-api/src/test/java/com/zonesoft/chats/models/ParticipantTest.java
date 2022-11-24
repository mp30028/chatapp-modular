package com.zonesoft.chats.models;

import static org.junit.jupiter.api.Assertions.*;
import static com.zonesoft.chats.data_generators.ParticipantDataGenerator.generateParticipant;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ParticipantTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ParticipantTest.class);
	
	@Test
	void testInstantiation() {
		Participant participant = generateParticipant();
		LOGGER.debug("Generated Participant Instantiated: participant = {}", participant);
		assertNotNull(participant);
		assertNotNull(participant.getPerson());
		assertNotNull(participant.getPerson().getOtherNames());
	}
}
