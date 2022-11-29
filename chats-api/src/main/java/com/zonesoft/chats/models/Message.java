package com.zonesoft.chats.models;

import static com.zonesoft.utils.helpers.ToStringBuilder.*;
import com.zonesoft.utils.helpers.ToStringBuilder;

import java.time.OffsetDateTime;

public class Message {
	private String id;
	private String messageText;
	private String senderParticipantId;
	private OffsetDateTime sentTime;

	
	public Message(String senderParticipantId, String messageText) {
		super();
		this.senderParticipantId = senderParticipantId;
		this.messageText = messageText;
		this.sentTime = OffsetDateTime.now();
	}
	
	public Message(String senderParticipantId, String messageText, OffsetDateTime sentTime) {
		super();
		this.senderParticipantId = senderParticipantId;
		this.messageText = messageText;
		this.sentTime = sentTime;
	}
	
	public Message(String id, String senderParticipantId, String messageText, OffsetDateTime sentTime) {
		super();
		this.id = id;
		this.senderParticipantId = senderParticipantId;
		this.messageText = messageText;
		this.sentTime = sentTime;
	}
	
	public Message(String id, String senderParticipantId, String messageText) {
		super();
		this.id = id;
		this.senderParticipantId = senderParticipantId;
		this.messageText = messageText;
		this.sentTime = OffsetDateTime.now();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getSenderParticipantId() {
		return senderParticipantId;
	}

	public void setSenderParticipantId(String senderParticipantId) {
		this.senderParticipantId = senderParticipantId;
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
					indent, key("sender-participant-id"), value(this.senderParticipantId), comma, newline,
					indent, key("sent-time"), value(this.sentTime), newline,
				rBrace
			);
	}
}

