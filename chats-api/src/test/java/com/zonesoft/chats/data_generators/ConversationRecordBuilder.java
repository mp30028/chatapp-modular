package com.zonesoft.chats.data_generators;

import static com.zonesoft.utils.data_generators.Generator.generateUUID;
import static com.zonesoft.utils.data_generators.Generator.generateRandomInt;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import com.zonesoft.chats.models.Conversation;
import com.zonesoft.chats.models.Message;
import com.zonesoft.chats.models.Participant;
import com.zonesoft.utils.data_generators.IRecordBuilder;
import com.zonesoft.utils.data_generators.RecordsGeneratorTemplate;

public class ConversationRecordBuilder implements IRecordBuilder<Conversation> {

	private String id;
	private String title = null;
	private List<Participant> participants; 
	private List<Message> messages;
	
	public ConversationRecordBuilder id() {
		this.id = generateUUID();
		return this;
	}

	public ConversationRecordBuilder id(String suppliedValue) {
		this.id = suppliedValue;
		return this;
	}
	
	public ConversationRecordBuilder title() {
		this.title = "Auto generated conversation  [" + Instant.now().toEpochMilli() + "]";
		return this;
	}

	public ConversationRecordBuilder title(String suppliedValue) {
		this.title = suppliedValue;
		return this;
	}
	
	
	public ConversationRecordBuilder participants() {
		Supplier<ParticipantRecordBuilder> supplier = ()->new ParticipantRecordBuilder().withDefaults();
		RecordsGeneratorTemplate<ParticipantRecordBuilder, Participant> generator = new RecordsGeneratorTemplate<ParticipantRecordBuilder, Participant>();		
		this.participants = generator.generate(supplier);
		return this;
	}
	
	public ConversationRecordBuilder participants(List<Participant> suppliedValue) {
		this.participants = suppliedValue;
		return this;
	}
	
	public ConversationRecordBuilder messages(List<Message> suppliedValue) {
		this.messages = suppliedValue;
		return this;
	}
	
	public ConversationRecordBuilder messages() {
		RecordsGeneratorTemplate<MessageRecordBuilder, Message> generator = new RecordsGeneratorTemplate<MessageRecordBuilder, Message>();		
		this.messages = generator.generate(messageBuilderSupplier());
		return this;
	}
	
	private Supplier<MessageRecordBuilder> messageBuilderSupplier(){
		Supplier<MessageRecordBuilder> supplier;
		if (Objects.isNull(this.participants)) {
			supplier = () -> new MessageRecordBuilder().withDefaults();
		}else {
			supplier = () -> new MessageRecordBuilder().senderPersonId(selectAParticipant()).withDefaults();
		}
		return supplier;
	}
	
	private String selectAParticipant() {
		int selectedIndex = generateRandomInt(0, this.participants.size()-1);
		return this.participants.get(selectedIndex).getPersonId();
	}
	
	public ConversationRecordBuilder withDefaults(boolean withId) {
		if (withId) {
			if (Objects.isNull(this.id)) this.id();
		}else {
			this.id = null;
		}
		if (Objects.isNull(this.title) || title.isBlank()) this.title();
		if (Objects.isNull(this.participants)) this.participants();
		if (Objects.isNull(this.messages)) this.messages();
		return this;
	}
	
	
	public ConversationRecordBuilder withDefaults() {
		return this.withDefaults(true);
	}

	@Override
	public Conversation build() {
		Conversation conversation = new Conversation(this.id, this.title);
		conversation.messages().addAll(messages);
		conversation.participants().addAll(participants);
		return conversation;
	}

}
