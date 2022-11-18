package com.zonesoft.chats.data_generators;

import static com.zonesoft.utils.data_generators.Generator.*;


import java.util.ArrayList;
import java.util.List;

import com.zonesoft.chats.models.Conversation;
import com.zonesoft.chats.models.Message;
import com.zonesoft.chats.models.Participant;
import com.zonesoft.chats.models.Person;

public class ConversationGenerator {
	private static Person createSinglePerson() {
		Person person = new Person(generateUUID(), generateNickname(), generateFirstName(generateGender()), generateLastName());
		return person;
	}
	
	private static List<Person>  generatePersons() {
		final int MAX_PERSONS = 3;
		final int MIN_PERSONS = 2;
		int numberOfPersons = generateRandomInt(MIN_PERSONS, MAX_PERSONS);
		List<Person> createdPersons = new ArrayList<>();
		for (int j=0; j < numberOfPersons; j++) {
			Person createdPerson = createSinglePerson();
			createdPersons.add(createdPerson);
		}
		return createdPersons;
	}
	
	private static List<Participant> generateParticipants(Conversation conversation){
		List<Person> persons = generatePersons();
		List<Participant> participants = new ArrayList<>();
		for(Person person: persons) {
			participants.add(new Participant(conversation, person));
		}
		return participants;
	}
	
	private static Message generateMessage(Conversation conversation){
		int numberOfParticipants = conversation.participants().size();
		int selectedParticipantIndex = generateRandomInt(0,numberOfParticipants-1);
		Participant sender = conversation.participants().get(selectedParticipantIndex);
		String messageText = generatePhrase();
		Message message = new Message(conversation,sender,messageText);
		return message;
	}
	
	private static List<Message> generateMessages(Conversation conversation){
		final int MIN_MESSAGES = 0;
		final int MAX_MESSAGES = 2;
		int numberOfMessages = generateRandomInt(MIN_MESSAGES, MAX_MESSAGES);
		List<Message> messages = new ArrayList<>();
		for(int j=0; j<numberOfMessages; j++) {
			messages.add(generateMessage(conversation));
		}
		return messages;
	}
	
	public static Conversation generateConversation() {
		Conversation conversation = new Conversation();
		conversation.participants().addAll(generateParticipants(conversation));
		conversation.messages().addAll(generateMessages(conversation));
		return conversation;
	}
}
