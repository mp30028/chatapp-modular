package com.zonesoft.chats.data_generators;


import static com.zonesoft.utils.data_generators.Generator.generateUUID;
import static com.zonesoft.utils.data_generators.Generator.generateDateTime;
import static com.zonesoft.utils.data_generators.Generator.generateNickname;

import java.time.OffsetDateTime;
import com.zonesoft.chats.models.Participant;
import com.zonesoft.utils.data_generators.IRecordBuilder;


public class ParticipantRecordBuilder implements IRecordBuilder< Participant> {
	private String id = null;
	private String personId = null;
	private String moniker = null;
	private OffsetDateTime participationStart = null;
	private OffsetDateTime participationEnd = null;
	
	public ParticipantRecordBuilder id() {
		this.id = generateUUID();
		return this;
	}
	
	public ParticipantRecordBuilder id(String suppliedValue) {
		this.id = suppliedValue;
		return this;
	}
	
	public ParticipantRecordBuilder personId() {
		this.personId = generateUUID();
		return this;
	}
	
	public ParticipantRecordBuilder moniker() {
		this.moniker = generateNickname();
		return this;
	}

	
	public ParticipantRecordBuilder personId(String suppliedValue) {
		this.personId = suppliedValue;
		return this;
	}
	
	public ParticipantRecordBuilder moniker(String suppliedValue) {
		this.moniker = suppliedValue;
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
		this.id().personId().moniker().participationStart().participationStart();
		return this;
	}
	
	@Override
	public Participant build() {
		Participant participant = new Participant(this.personId,this.moniker, this.participationStart);
		participant.setId(id);
		participant.setParticipationEnd(participationEnd);
		return participant;
	}
}
