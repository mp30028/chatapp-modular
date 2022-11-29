package com.zonesoft.chats.data_generators;

import static com.zonesoft.utils.data_generators.Generator.generateUUID;
import static com.zonesoft.utils.data_generators.Generator.generateRandomInt;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import com.zonesoft.chats.models.Conversation;
import com.zonesoft.chats.models.Message;
import com.zonesoft.chats.models.Participant;
import com.zonesoft.utils.data_generators.IRecordGenerator;
import com.zonesoft.utils.data_generators.RecordsGeneratorTemplate;

public class ConversationRecordBuilder implements IRecordGenerator<ConversationRecordBuilder, Conversation> {

	private String id;
	private List<Participant> participants; 
	private List<Message> messages;
	
	@Override
	public ConversationRecordBuilder id() {
		this.id = generateUUID();
		return this;
	}

	@Override
	public ConversationRecordBuilder id(String suppliedValue) {
		this.id = suppliedValue;
		return this;
	}
	
	public ConversationRecordBuilder participants() {
		Supplier<ParticipantRecordBuilder> supplier = ()->new ParticipantRecordBuilder();
		RecordsGeneratorTemplate<ParticipantRecordBuilder, Participant> generator = new RecordsGeneratorTemplate<ParticipantRecordBuilder, Participant>();		
		this.participants = generator.generate(supplier);
		return this;
	}
	
	public ConversationRecordBuilder participants(List<Participant> suppliedValue) {
		this.participants = suppliedValue;
		return this;
	}

	public ConversationRecordBuilder messages() {
		Supplier<MessageRecordBuilder> supplier = ()->new MessageRecordBuilder();
		RecordsGeneratorTemplate<MessageRecordBuilder, Message> generator = new RecordsGeneratorTemplate<MessageRecordBuilder, Message>();		
		this.messages = generator.generate(supplier);
		correlateMessagesToParticipants();
		return this;
	}
	
	public ConversationRecordBuilder correlateMessagesToParticipants() {
		if(Objects.nonNull(participants)) {
			for(Message message: this.messages) {
				int selectedIndex = generateRandomInt(0, participants.size()-1);
				message.setSenderParticipantId(participants.get(selectedIndex).getId());
			}
		}
		return this;
	}
	
	public ConversationRecordBuilder messages(List<Message> suppliedValue) {
		this.messages = suppliedValue;
		return this;
	}
	
	@Override
	public ConversationRecordBuilder withDefaults() {
		return this.id().participants().messages();
	}

	@Override
	public Conversation generate() {
		Conversation conversation = new Conversation(this.id);
		conversation.messages().addAll(messages);
		conversation.participants().addAll(participants);
		return conversation;
	}

}
