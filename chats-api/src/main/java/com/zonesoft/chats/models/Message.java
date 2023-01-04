package com.zonesoft.chats.models;

import static com.zonesoft.utils.helpers.ToStringBuilder.*;
import com.zonesoft.utils.helpers.ToStringBuilder;

import java.time.OffsetDateTime;

public class Message {
	private String id = null;
	private String messageText = null;
	private String senderPersonId = null;
	private OffsetDateTime sentTime = null;
	
	public Message() {
		super();
	}
	
	public Message(String senderPersonId, String messageText) {
		super();
		this.senderPersonId = senderPersonId;
		this.messageText = messageText;
		this.sentTime = OffsetDateTime.now();
	}
	
	public Message(String senderPersonId, String messageText, OffsetDateTime sentTime) {
		super();
		this.senderPersonId = senderPersonId;
		this.messageText = messageText;
		this.sentTime = sentTime;
	}
	
	public Message(String id, String senderPersonId, String messageText, OffsetDateTime sentTime) {
		super();
		this.id = id;
		this.senderPersonId = senderPersonId;
		this.messageText = messageText;
		this.sentTime = sentTime;
	}
	
	public Message(String id, String senderPersonId, String messageText) {
		super();
		this.id = id;
		this.senderPersonId = senderPersonId;
		this.messageText = messageText;
		this.sentTime = OffsetDateTime.now();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSenderPersonId() {
		return senderPersonId;
	}

	public void setSenderPersonId(String senderPersonId) {
		this.senderPersonId = senderPersonId;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public OffsetDateTime getSentTime() {
		return sentTime;
	}

	
	public void setSentTime(OffsetDateTime sentTime) {
		this.sentTime = sentTime;
	}

	@Override
	public String toString() {
		return new ToStringBuilder()
			.build(
				lBrace, newline,
					indent, key("message-id"), value(this.id), comma, newline,
					indent, key("message-text"), value(this.messageText), comma,  newline,
					indent, key("sender-person-id"), value(this.senderPersonId), comma, newline,
					indent, key("sent-time"), value(this.sentTime), newline,
				rBrace
			);
	}
}

