package com.zonesoft.participants.models;

import static com.zonesoft.utils.helpers.ToStringBuilder.*;
import com.zonesoft.utils.helpers.ToStringBuilder;
import java.time.OffsetDateTime;

public class Participant {
	private String id;
	private final Person person;
	private final OffsetDateTime participationStart;
	private OffsetDateTime participationEnd = null;
	
	
	public Participant(Person person) {
		super();
		this.person = person;
		this.participationStart = OffsetDateTime.now();
	}
	
	public Participant(Person person, OffsetDateTime participationStart) {
		super();
		this.person = person;
		this.participationStart = participationStart;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
		
	public Person getPerson() {
		return person;
	}
	
	public OffsetDateTime getParticipationStart() {
		return participationStart;
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
					indent, key("participant-id"), 		value(this.id), 					comma, newline,
					indent, key("person"), 				objectValue(this.person), 			comma, newline,
					indent, key("participation-start"), value(this.participationStart), 	comma, newline,
					indent, key("participation-end"), 	value(this.participationEnd), 		newline,
				rBrace
			);
	}

	
}