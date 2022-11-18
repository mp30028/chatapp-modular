package com.zonesoft.chats.models;

import static com.zonesoft.utils.helpers.ToStringBuilder.*;
import com.zonesoft.utils.helpers.ToStringBuilder;
import java.time.OffsetDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;



public class Participant {
	@Id private String id;
	@DBRef private final Conversation conversation;
	@DBRef private final Person person;
	private final OffsetDateTime participationStart;
	private OffsetDateTime participationEnd = null;
	
	
	public Participant(Conversation conversation, Person person) {
		super();
		this.conversation = conversation;
		this.person = person;
		this.participationStart = OffsetDateTime.now();
	}
	
	public Participant(Conversation conversation, Person person, OffsetDateTime participationStart) {
		super();
		this.conversation = conversation;
		this.person = person;
		this.participationStart = participationStart;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Conversation getConversation() {
		return conversation;
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
					indent, key("conversation-id"), 	value(this.conversation.getId()), 	comma, newline,
					indent, key("person"), 				objectValue(this.person), 			comma, newline,
					indent, key("participation-start"), value(this.participationStart), 	comma, newline,
					indent, key("participation-end"), 	value(this.participationEnd), 		newline,
				rBrace
			);
	}

	
}