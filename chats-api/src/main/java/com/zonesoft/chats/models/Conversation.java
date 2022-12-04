package com.zonesoft.chats.models;

import com.zonesoft.utils.helpers.ToStringBuilder;
import static com.zonesoft.utils.helpers.ToStringBuilder.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "conversations")
public class Conversation {
	@Id private String id;
	private List<Participant> participants = new ArrayList<>(); 
	private List<Message> messages = new ArrayList<>();
	
	public Conversation() {
		super();
	}
	
	public Conversation(String id) {
		super();
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public List<Participant> participants() {
		return participants;
	}
	
	public List<Message> messages() {
		return messages;
	}
	
	public List<Participant> getParticipants() {
		return participants;
	}
	
	void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public List<Message> getMessages() {
		return messages;
	}

	void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder()
		.build(
				lBrace, newline,
					indent, key("id"), value(this.id), comma, newline,
					indent, key("participants"), objectValue(this.participants), comma, newline,
					indent, key("messages"), objectValue(this.messages),  newline,
				rBrace
		);
	}
}
