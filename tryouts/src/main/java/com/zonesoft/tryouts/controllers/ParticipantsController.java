package com.zonesoft.tryouts.controllers;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.zonesoft.tryouts.data_generators.ParticipantDataGenerator.generateParticipant;
//import static com.zonesoft.tryouts.data_generators.ParticipantDataGenerator.generateParticipant;
//import static com.zonesoft.tryouts.data_generators.PersonDataGenerator.generatePersons;
import static com.zonesoft.tryouts.data_generators.PersonDataGenerator.generatePersons;

import com.zonesoft.tryouts.models.Participant;
import com.zonesoft.tryouts.models.Person;
import com.zonesoft.tryouts.repositories.ParticipantRepository;
import com.zonesoft.tryouts.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/participants")
public class ParticipantsController {
    
	private final ParticipantRepository participantsRepository;
	private final PersonRepository personRepository;
    
    @Autowired
    public ParticipantsController(ParticipantRepository participantsRepository, PersonRepository personRepository) {
    	super();
    	this.participantsRepository = participantsRepository;
		this.personRepository = personRepository;	
    }

    
    @GetMapping("/regenerate-participants")
	public Flux<Participant>  deleteAndCreateParticipantsData() {
    	return participantsRepository.insert(
    				participantsRepository.deleteAll()
    				.thenMany(deleteAndCreatePersonsData())
    				.map(p -> generateParticipant(p))
    			);   		
    }
    
    @GetMapping("/regenerate-participants-v1")
	public Flux<Participant>  deleteAndCreateParticipantsDataV1() {
    	Flux<Participant> resultFlux = 
    			participantsRepository.deleteAll()
    			.thenMany(deleteAndCreatePersonsData())
    			.map(p -> generateParticipant(p));   		
    	return participantsRepository.insert(resultFlux);
    }
    
    @GetMapping("/regenerate-persons")
	public Flux<Person>  deleteAndCreatePersonsData() {
    	return personRepository.deleteAll()
    	.thenMany(personRepository.insert(generatePersons()));
	}
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Participant> insert(@RequestBody Participant participant){
        return participantsRepository.insert(participant);
    }

    @GetMapping
    public Flux<Participant> findAll(){
        return participantsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Participant>> findById(@PathVariable String id){
        Mono<Participant> participantMono = participantsRepository.findById(id);
        return participantMono.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Participant>> update(@PathVariable String id, @RequestBody Participant participant){
        return participantsRepository.save(participant)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable String id){
        return participantsRepository.deleteById(id)
                .map( r -> ResponseEntity.ok().<Void>build());
//                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}