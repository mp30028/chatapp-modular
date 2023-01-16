package com.zonesoft.chats.models;

import static com.zonesoft.utils.helpers.ToStringBuilder.*;
import com.zonesoft.utils.helpers.ToStringBuilder;
import java.time.OffsetDateTime;

public class Participant {
	private String personId = null;
	private OffsetDateTime participationStart = null;
	private OffsetDateTime participationEnd = null;
	
	public Participant() {
		super();
	}
	
	public Participant(String personId) {
		super();
		this.personId = personId;
		this.participationStart = OffsetDateTime.now();
	}
	
	public Participant(String personId, OffsetDateTime participationStart) {
		super();
		this.personId = personId;
		this.participationStart = participationStart;
	}
	
	public String getPersonId() {
		return personId;
	}
	
	public void setPersonId(String personId) {
		this.personId = personId;
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
					indent, key("personId"), 			value(this.personId), 			comma, newline,
					indent, key("participationStart"), value(this.participationStart), comma, newline,
					indent, key("participationEnd"), 	value(this.participationEnd), 	newline,
				rBrace
			);
	}

	
}