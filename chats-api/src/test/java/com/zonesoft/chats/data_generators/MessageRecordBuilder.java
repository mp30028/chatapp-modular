package com.zonesoft.chats.data_generators;

import static com.zonesoft.utils.data_generators.Generator.generatePhrase;
import static com.zonesoft.utils.data_generators.Generator.generateUUID;
import static com.zonesoft.utils.data_generators.Generator.generateDateTime;
import java.time.OffsetDateTime;

import com.zonesoft.chats.models.Message;
import com.zonesoft.utils.data_generators.IRecordBuilder;

public class MessageRecordBuilder implements IRecordBuilder<Message> {
	private String id;
	private String messageText;
	private String senderParticipantId;
	private OffsetDateTime sentTime;
	
	public MessageRecordBuilder id() {
		this.id = generateUUID();
		return this;
	}
	
	public MessageRecordBuilder id(String suppliedValue) {
		this.id = suppliedValue;
		return this;
	}
	
	public MessageRecordBuilder messageText() {
		this.messageText = generatePhrase();
		return this;
	}
	
	public MessageRecordBuilder messageText(String suppliedValue) {
		this.messageText = suppliedValue;
		return this;
	}
	
	public MessageRecordBuilder senderParticipantId() {
		this.senderParticipantId = generateUUID();
		return this;
	}
	
	public MessageRecordBuilder senderParticipantId(String suppliedValue) {
		this.senderParticipantId = suppliedValue;
		return this;
	}
	
	public MessageRecordBuilder sentTime() {
		this.sentTime = generateDateTime();
		return this;
	}
	
	public MessageRecordBuilder sentTime(OffsetDateTime suppliedValue) {
		this.sentTime = suppliedValue;
		return this;
	}
	
	public MessageRecordBuilder withDefaults() {
		return this.id().messageText().senderParticipantId().sentTime();
	}
	
	@Override
	public Message build() {
		Message message = new Message(this.id, this.senderParticipantId, this.messageText, this.sentTime);
		return message;
	}

}
