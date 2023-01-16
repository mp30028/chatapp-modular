package com.zonesoft.chats.data_generators;


import static com.zonesoft.utils.data_generators.Generator.generateUUID;
import static com.zonesoft.utils.data_generators.Generator.generateDateTime;

import java.time.OffsetDateTime;
import java.util.Objects;

import com.zonesoft.chats.models.Participant;
import com.zonesoft.utils.data_generators.IRecordBuilder;


public class ParticipantRecordBuilder implements IRecordBuilder< Participant> {
	private String personId = null;
	private OffsetDateTime participationStart = null;
	private OffsetDateTime participationEnd = null;
	
	public ParticipantRecordBuilder personId() {
		this.personId = generateUUID();
		return this;
	}
	
	public ParticipantRecordBuilder personId(String suppliedValue) {
		this.personId = suppliedValue;
		return this;
	}

	public ParticipantRecordBuilder participationStart() {
		this.participationStart = generateDateTime();
		return this;
	}
	
	public ParticipantRecordBuilder participationStart(OffsetDateTime suppliedValue) {
		this.participationStart = suppliedValue;
		return this;
	}
	
	public ParticipantRecordBuilder participationEnd() {
		this.participationEnd = generateDateTime();
		return this;
	}
	
	public ParticipantRecordBuilder participationEnd(OffsetDateTime suppliedValue) {
		this.participationEnd = suppliedValue;
		return this;
	}
	

	public ParticipantRecordBuilder withDefaults() {
		if (Objects.isNull(this.personId)) this.personId();
		if (Objects.isNull(this.participationStart)) this.participationStart();
		return this;
	}
	
	@Override
	public Participant build() {
		Participant participant = new Participant(this.personId, this.participationStart);
		participant.setParticipationEnd(this.participationEnd);
		return participant;
	}
}
