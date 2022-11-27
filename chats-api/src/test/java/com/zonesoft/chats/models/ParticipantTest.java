package com.zonesoft.chats.models;

import com.zonesoft.chats.data_generators.ParticipantDataGenerator;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ParticipantTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ParticipantTest.class);
	
	@Test
	void testInstantiation() {
		Participant participant = new ParticipantDataGenerator().withDefaults().generate();
		LOGGER.debug("Generated Participant Instantiated: participant = {}", participant);
		assertNotNull(participant);
		assertNotNull(participant.getId());
		assertNotNull(participant.getPersonId());
		assertNotNull(participant.getParticipationStart());
		assertNull(participant.getParticipationEnd());
	}
}
