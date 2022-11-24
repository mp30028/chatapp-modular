package com.zonesoft.chats.models;

import static com.zonesoft.utils.helpers.ToStringBuilder.*;
import com.zonesoft.utils.helpers.ToStringBuilder;

import java.time.OffsetDateTime;

public class Message {
	private String id;
	private final String messageText;
	private final String senderParticipantId;
	private final OffsetDateTime sentTime;
	private final String conversationId;
	
	public Message(String conversationId, String senderParticipantId, String messageText) {
		super();
		this.conversationId = conversationId;
		this.senderParticipantId = senderParticipantId;
		this.messageText = messageText;
		this.sentTime = OffsetDateTime.now();
	}
	
	public Message(String conversationId, String senderParticipantId, String messageText, OffsetDateTime sentTime) {
		super();
		this.conversationId = conversationId;
		this.senderParticipantId = senderParticipantId;
		this.messageText = messageText;
		this.sentTime = sentTime;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getConversationId() {
		return conversationId;
	}

	public String getSenderParticipantId() {
		return senderParticipantId;
	}

	public String getMessageText() {
		return messageText;
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
					indent, key("message-text"), value(this.messageText), comma,  newline,
					indent, key("sender-participant-id"), value(this.senderParticipantId), comma, newline,
					indent, key("conversation-id"), value(this.conversationId), comma, newline,
					indent, key("sent-time"), value(this.sentTime), newline,
				rBrace
			);
	}
}

