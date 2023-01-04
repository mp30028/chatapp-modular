package com.zonesoft.chats.models;

import static com.zonesoft.utils.helpers.ToStringBuilder.*;
import com.zonesoft.utils.helpers.ToStringBuilder;
import java.time.OffsetDateTime;

public class Participant {
	private String personId = null;
	private String moniker = null;
	private OffsetDateTime participationStart = null;
	private OffsetDateTime participationEnd = null;
	
	public Participant() {
		super();
	}
	
	public Participant(String personId, String moniker) {
		super();
		this.personId = personId;
		this.moniker = moniker;
		this.participationStart = OffsetDateTime.now();
	}
	
	public Participant(String personId, String moniker, OffsetDateTime participationStart) {
		super();
		this.personId = personId;
		this.moniker = moniker;
		this.participationStart = participationStart;
	}
	
	public String getPersonId() {
		return personId;
	}
	
	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getMoniker() {
		return moniker;
	}
	
	public void setMoniker(String moniker) {
		this.moniker = moniker;
	}

	public OffsetDateTime getParticipationStart() {
		return participationStart;
	}
	
	public void setParticipationStart(OffsetDateTime participationStart) {
		this.participationStart = participationStart;
	}
	

	public OffsetDateTime getParticipationEnd() {
		return participationEnd;
	}
	
	public void setParticipationEnd(OffsetDateTime participationEnd) {
		this.participationEnd = participationEnd;
	}

	@Override
	public String toString() {
		return new ToStringBuilder()
			.build(
				lBrace, newline,
					indent, key("person-id"), 			value(this.personId), 			comma, newline,
					indent, key("moniker"), 			value(this.moniker), 			comma, newline,
					indent, key("participation-start"), value(this.participationStart), comma, newline,
					indent, key("participation-end"), 	value(this.participationEnd), 	newline,
				rBrace
			);
	}

	
}