package com.zonesoft.tryouts.repositories;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.zonesoft.tryouts.models.Person;

import reactor.core.publisher.Flux;

@Repository
public interface PersonRepository extends ReactiveMongoRepository<Person, String> {
	
	public Flux<Person> findByMoniker(String moniker);

}
