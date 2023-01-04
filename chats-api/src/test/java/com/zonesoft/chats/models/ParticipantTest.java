package com.zonesoft.chats.models;

import com.zonesoft.chats.data_generators.ParticipantRecordBuilder;
import com.zonesoft.utils.data_generators.RecordsGeneratorTemplate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ParticipantTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ParticipantTest.class);
	
	@Test
	void testSingleParticipantInstantiation() {
		Participant participant = new ParticipantRecordBuilder().withDefaults().build();
		LOGGER.debug("ParticipantTest.testSingleParticipantInstantiation: generated-participant = {}", participant);
		assertNotNull(participant);
//		assertNotNull(participant.getId());
		assertNotNull(participant.getPersonId());
		assertNotNull(participant.getMoniker());
		assertNotNull(participant.getParticipationStart());
		assertNull(participant.getParticipationEnd());
	}
	
	@Test
	void testMultipleParticipantsInstantiation() {
		Supplier<ParticipantRecordBuilder> supplier = ()->new ParticipantRecordBuilder().withDefaults();
		RecordsGeneratorTemplate<ParticipantRecordBuilder, Participant> generator = new RecordsGeneratorTemplate<ParticipantRecordBuilder, Participant>();
		List<Participant> participants = generator.generate(supplier);
		LOGGER.debug("ParticipantTest.testMultipleParticipantsInstantiation: generated-participants = {}", participants);
		for(Participant participant: participants) {
			assertNotNull(participant);
//			assertNotNull(participant.getId());
			assertNotNull(participant.getPersonId());
			assertNotNull(participant.getMoniker());
			assertNotNull(participant.getParticipationStart());
			assertNull(participant.getParticipationEnd());
		}
	}
}
