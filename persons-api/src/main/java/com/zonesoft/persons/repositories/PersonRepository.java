package com.zonesoft.persons.repositories;


//import org.springframework.data.mongodb.repository.Query;
//import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
//import org.springframework.data.mongodb.repository.Tailable;
//import org.springframework.stereotype.Repository;

import com.zonesoft.persons.models.Person;

import reactor.core.publisher.Flux;

//@Repository
public interface PersonRepository extends ReactiveMongoRepository<Person, String> {
	
	public Flux<Person> findByMoniker(String moniker);
	
//	  @Tailable
//	  @Query("{}")
//	  Flux<Person> streamAll();

}
