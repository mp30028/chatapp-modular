package com.zonesoft.chats.events;

import static com.zonesoft.utils.helpers.ToStringBuilder.*;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.zonesoft.chats.models.Conversation;
import com.zonesoft.utils.helpers.ToStringBuilder;

@Document(collection = "persistenceEvents")
public class PersistenceEvent {
	@Id private String id;
	private List<Conversation>  conversations;
	private PersistenceEventType eventType;
	
	public enum PersistenceEventType {
		SAVE,
		SAVE_ALL,
		DELETE,
		DELETE_ALL,
		OTHER
	}
	
	public PersistenceEvent(PersistenceEventType eventType, List<Conversation>  conversations) {
		this.conversations = conversations;
		this.eventType = eventType;
	}
	
	public PersistenceEvent() {
		this.conversations = null;
		this.eventType = PersistenceEventType.OTHER ;
	}
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public PersistenceEventType getEventType() {
		return eventType;
	}

	public void setEventType(PersistenceEventType eventType) {
		this.eventType = eventType;
	}

	public List<Conversation>  getConversations() {
		return conversations;
	}
	
	public void setConversations(List<Conversation> conversations) {
		this.conversations = conversations;
	}

	@Override
	public String toString() {		
		return new ToStringBuilder()
		.build(
				lBrace, newline,
					indent, key("conversations"), objectValue(this.conversations), comma, newline,
					indent, key("persistence-event-type"), value(this.eventType), newline,
				rBrace
		);
	}
}


