package com.zonesoft.tryouts.data_generators;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.zonesoft.tryouts.models.Participant;
import com.zonesoft.tryouts.models.Person;
import com.zonesoft.tryouts.repositories.ParticipantRepository;
import com.zonesoft.tryouts.repositories.PersonRepository;
import static com.zonesoft.tryouts.data_generators.PersonDataGenerator.generatePersons;

public class ParticipantDataGenerator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ParticipantDataGenerator.class);
	
	private ParticipantRepository participantRepository;
	private PersonRepository personRepository;
	
	public ParticipantDataGenerator(ParticipantRepository participantRepository, PersonRepository personRepository) {
		super();
		this.participantRepository = participantRepository;
		this.personRepository = personRepository;
	}

	public void deleteAllPersonsInDb() {
		personRepository.deleteAll().block();
		LOGGER.debug("deleteAllPersonsInDb completed");
	}
	
	public void deleteAllParticipantsInDb() {
		participantRepository.deleteAll().block();
		deleteAllPersonsInDb();
		LOGGER.debug("deleteAllParticipantsInDb completed");
	}
	
	public String generateParticipantsToDb() {
		List<Person> persons =  generatePersons();
		persons = personRepository.insert(persons).collectList().block();
		List<Participant> participants = generateParticipants(persons);
		participantRepository.insert(participants).collectList().block();
		List<Participant> result = participantRepository.findAll().collectList().block();
		LOGGER.debug("generateParticipantsToDb: result = {}", result);
		return result.toString();
	}
	
	public static Participant generateParticipant(Person person){
		Participant participant = new Participant(person.getId());		
		return participant;
	}
	
	public static List<Participant> generateParticipants(List<Person> persons){
		List<Participant> participants = new ArrayList<>();
		for(Person person: persons) {
			Participant participant = new Participant(person.getId());
			participants.add(participant);
		}		
		return participants;
	}
}
