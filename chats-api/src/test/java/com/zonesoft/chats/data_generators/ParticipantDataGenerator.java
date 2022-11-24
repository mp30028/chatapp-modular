package com.zonesoft.chats.data_generators;

import static com.zonesoft.chats.data_generators.PersonDataGenerator.*;

import java.util.ArrayList;
import java.util.List;

import com.zonesoft.chats.models.Participant;
import com.zonesoft.chats.models.Person;

public class ParticipantDataGenerator {

	public static List<Participant> generateParticipants(){
		List<Person> persons = generatePersonsWithIds();
		List<Participant> participants = new ArrayList<>();
		for(Person person: persons) {
			participants.add(new Participant(person));
		}
		return participants;
	}
	
	public static Participant generateParticipant(){		
		return new Participant(generatePersonWithId());
	}
	
}
