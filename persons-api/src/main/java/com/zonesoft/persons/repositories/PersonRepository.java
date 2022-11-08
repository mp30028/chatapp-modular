package com.zonesoft.persons.repositories;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.zonesoft.persons.models.Person;

@Repository
public interface PersonRepository extends ReactiveMongoRepository<Person, String> {

}
