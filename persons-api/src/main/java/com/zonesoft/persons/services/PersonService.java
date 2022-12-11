package com.zonesoft.persons.services;

import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zonesoft.persons.models.Person;
import com.zonesoft.persons.repositories.PersonRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonService {
	
	private final PersonRepository repository;
	
//	@Autowired
	public PersonService(PersonRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Mono<Person> insert(Person person){
		return repository.insert(person);
	}
	
	public Mono<Person> update(Person person){
		return repository.save(person);
	}
	
	public Flux<Person> saveAll(List<Person> persons){
    	return repository.saveAll(persons);
    }
    
	public Mono<Person> findById(String id){
    	return repository.findById(id);
    }
    
	public Flux<Person> findAll(){
    	return repository.findAll();
    }
    
	public Mono<Void> deleteAll(){
    	return repository.deleteAll();
    }
    
	public Mono<Void> deleteById(String id){
    	return repository.deleteById(id);
    }
	
	public Flux<Person> findByMoniker(String moniker){
    	return repository.findByMoniker(moniker);
    }

	public Flux<Person> streamAll() {
		return repository.streamAll();
	}
}
