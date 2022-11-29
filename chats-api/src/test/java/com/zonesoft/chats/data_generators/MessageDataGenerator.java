package com.zonesoft.chats.data_generators;

import static com.zonesoft.utils.data_generators.Generator.generatePhrase;
import static com.zonesoft.utils.data_generators.Generator.generateUUID;
import static com.zonesoft.utils.data_generators.Generator.generateRandomInt;

import java.util.ArrayList;
import java.util.List;

import com.zonesoft.chats.models.Message;

public class MessageDataGenerator {
	private static final int MIN_MESSAGES = 0;
	private static final int MAX_MESSAGES = 30;
	
	public static Message generateMessageWithId( String senderParticipantId){
		Message message = new Message(senderParticipantId,generatePhrase());
		message.setId(generateUUID());
		return message;
	}
	
	public static Message generateMessageWithId(){
		Message message =  new Message(generateUUID(), generatePhrase());
		message.setId(generateUUID());
		return message;
	}
	
	
	public static Message generateMessage( String senderParticipantId){
		Message message = new Message(senderParticipantId,generatePhrase());
		return message;
	}
	
	public static Message generateMessage(){
		Message message =  new Message(generateUUID(), generatePhrase());
		return message;
	}
	
	public static List<Message> generateMessages() {
		int numberOfMessages = generateRandomInt(MIN_MESSAGES, MAX_MESSAGES);
		List<Message> messages = new ArrayList<>();
		for (int j = 0; j < numberOfMessages; j++) {
			messages.add(generateMessage());
		}
		return messages;
	}
	
	public static List<Message> generateMessages( String senderParticipantId) {
		int numberOfMessages = generateRandomInt(MIN_MESSAGES, MAX_MESSAGES);
		List<Message> messages = new ArrayList<>();
		for (int j = 0; j < numberOfMessages; j++) {
			messages.add(generateMessage(senderParticipantId));
		}
		return messages;
	}
	
	public static List<Message> generateMessagesWithId() {
		List<Message> messages = generateMessages();
		for (Message message : messages) {
			message.setId(generateUUID());
		}
		return messages;
	}
	
	public static List<Message> generateMessagesWithId( String senderParticipantId) {
		List<Message> messages = generateMessages( senderParticipantId);
		for (Message message : messages) {
			message.setId(generateUUID());
		}
		return messages;
	}
}
