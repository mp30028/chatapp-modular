package com.zonesoft.chats.data_generators;


import static com.zonesoft.utils.data_generators.Generator.generateUUID;
import static com.zonesoft.utils.data_generators.Generator.generateDateTime;

import java.time.OffsetDateTime;
import com.zonesoft.chats.models.Participant;


public class ParticipantDataGenerator {
	private String id = null;
	private String personId = null;
	private OffsetDateTime participationStart = null;
	private OffsetDateTime participationEnd = null;
	
	public ParticipantDataGenerator id() {
		this.id = generateUUID();
		return this;
	}
	
	public ParticipantDataGenerator id(String suppliedValue) {
		this.id = suppliedValue;
		return this;
	}
	
	public ParticipantDataGenerator personId() {
		this.personId = generateUUID();
		return this;
	}
	
	public ParticipantDataGenerator personId(String suppliedValue) {
		this.personId = suppliedValue;
		return this;
	}
	
	public ParticipantDataGenerator participationStart() {
		this.participationStart = generateDateTime();
		return this;
	}
	
	public ParticipantDataGenerator participationStart(OffsetDateTime suppliedValue) {
		this.participationStart = suppliedValue;
		return this;
	}
	
	public ParticipantDataGenerator participationEnd() {
		this.participationEnd = generateDateTime();
		return this;
	}
	
	public ParticipantDataGenerator participationEnd(OffsetDateTime suppliedValue) {
		this.participationEnd = suppliedValue;
		return this;
	}
	
	public ParticipantDataGenerator withDefaults() {
		this.id().personId().participationStart().participationStart();
		return this;
	}
	
	public Participant generate() {
		Participant participant = new Participant(this.personId, this.participationStart);
		participant.setId(id);
		participant.setParticipationEnd(participationEnd);
		return participant;
	}
}
