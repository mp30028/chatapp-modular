package com.zonesoft.tryouts.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zonesoft.tryouts.models.Participant;
import com.zonesoft.tryouts.models.Person;
import com.zonesoft.tryouts.repositories.ParticipantRepository;
import com.zonesoft.tryouts.repositories.PersonRepository;

import reactor.core.publisher.Mono;

@Service
public class ParticipantService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ParticipantService.class);
	
	private final ParticipantRepository participantsRepository;
	private final PersonRepository personRepository;
    
    @Autowired
    public ParticipantService(ParticipantRepository participantsRepository, PersonRepository personRepository) {
    	super();
    	this.participantsRepository = participantsRepository;
		this.personRepository = personRepository;	
    }
      
    public Mono<Participant> findById_DO_NOT_USE(String id){
    	Mono<Participant> participantMono = 
    		participantsRepository
    		.findById(id)
    		.map(prtcpnt -> {
    			LOGGER.debug("FROM ParticipantService.findById participantMono Initialisation Step-1: prtcpnt={}",prtcpnt); 
    			return prtcpnt;
    		})
    		.map(prtcpnt -> {
    			LOGGER.debug("FROM ParticipantService.findById participantMono Initialisation Step-2: prtcpnt={}",prtcpnt); 
    			personRepository
    				.findById(prtcpnt.getPersonId())
    				.map(prsn ->{
    					prtcpnt.setPerson(prsn);
    					LOGGER.debug("FROM ParticipantService.findById participantMono Initialisation Step-3: prtcpnt={}",prtcpnt);
    					return prtcpnt;
    				}).subscribe();
    			return prtcpnt;
    		});
    	return participantMono;
    }
    
    
    public Mono<Participant> findById(String id){
    	Mono<Participant> participantMono = 
    		participantsRepository
    		.findById(id)
    		.map(prtcpnt -> {
    			LOGGER.debug("FROM ParticipantService.findById participantMono Pre-Initialisation: participant={}",prtcpnt); 
    			return prtcpnt;
    		});
    	
    	Mono<String> personIdMono = participantMono.map(prtcpnt -> prtcpnt.getPersonId());
    	Mono<Person> personMono = personRepository.findById(personIdMono);
    	Mono<Participant> enrichedParticipantMono = 
    		Mono.zip(participantMono, personMono)
    		.map((tuple) ->{
    			Participant participant = tuple.getT1();
    			Person person = tuple.getT2();
    			participant.setPerson(person);
    			LOGGER.debug("FROM ParticipantService.findById enrichedParticipantMono Post-Initialisation: participant={}",participant);
    			return participant;
    		});
    	return enrichedParticipantMono;
    }   
}
