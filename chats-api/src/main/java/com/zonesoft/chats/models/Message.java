package com.zonesoft.chats.models;

import static com.zonesoft.utils.helpers.ToStringBuilder.*;
import com.zonesoft.utils.helpers.ToStringBuilder;

import java.time.OffsetDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Message {
	@Id private String id;
	@DBRef private final Conversation conversation;
	private final String messageText;
	@DBRef private final Participant sender;
	private final OffsetDateTime sentTime;
	
	public Message(Conversation conversation, Participant sender, String messageText) {
		super();
		this.conversation = conversation;
		this.sender = sender;
		this.messageText = messageText;
		this.sentTime = OffsetDateTime.now();
	}
	
	public Message(Conversation conversation, Participant sender, String messageText, OffsetDateTime sentTime) {
		super();
		this.conversation = conversation;
		this.sender = sender;
		this.messageText = messageText;
		this.sentTime = sentTime;
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
	
	public String getMessageText() {
		return messageText;
	}


	public Participant getSender() {
		return sender;
	}


	public OffsetDateTime getSentTime() {
		return sentTime;
	}

	
	@Override
	public String toString() {
		return new ToStringBuilder()
			.build(
				lBrace, newline,
					indent, key("message-id"), value(this.id), comma, newline,
					indent, key("conversation-id"), value(this.conversation.getId()), comma, newline,
					indent, key("message-text"), value(this.messageText), comma,  newline,
					indent, key("sender"), objectValue(this.sender), comma, newline,
					indent, key("conversation-id"), value(this.sender), comma, newline,
					indent, key("sent-time"), value(this.sentTime), newline,
				rBrace
			);
	}
}

