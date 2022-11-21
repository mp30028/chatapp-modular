package com.zonesoft.tryouts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zonesoft.tryouts.models.Person;
import com.zonesoft.tryouts.repositories.PersonRepository;

import reactor.core.publisher.Mono;

@Service
public class PersonLookup {
	
	private static PersonRepository repository;
	
	@Autowired
	public PersonLookup(PersonRepository repository) {
		super();
		PersonLookup.repository = repository;				
	}
	
	public static Mono<Person> lookupPersonById(String personId) {
		return repository.findById(personId);
	}
	
}
