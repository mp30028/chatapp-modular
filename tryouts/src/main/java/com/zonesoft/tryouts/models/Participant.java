package com.zonesoft.tryouts.models;

import static com.zonesoft.utils.helpers.ToStringBuilder.*;
import com.zonesoft.utils.helpers.ToStringBuilder;

import java.time.OffsetDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "participants")
public class Participant {
	@Id private String id;
		
	private OffsetDateTime participationStart;
	private OffsetDateTime participationEnd = null;
	private String personId;

	private Person person;
	
	
	public Participant() {
		super();
		this.participationStart = OffsetDateTime.now();
	}
	
	public Participant(String personId) {
		super();
		this.personId = personId; 
		this.participationStart = OffsetDateTime.now();
	}
	
	public Participant(String personId, OffsetDateTime participationStart) {
		super();		
		this.participationStart = participationStart;
		this.personId = person.getId();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public Person getPerson() {		
		return this.person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	@ReadOnlyProperty
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
					indent, key("participant-id"), 		value(this.id), 					comma, newline,
					indent, key("person-id"), 			value(this.personId), 				comma, newline,
					indent, key("person"), 				objectValue(this.getPerson()), 		comma, newline,
					indent, key("participation-start"), value(this.participationStart), 	comma, newline,
					indent, key("participation-end"), 	value(this.participationEnd), 		newline,
				rBrace
			);
	}

	
}