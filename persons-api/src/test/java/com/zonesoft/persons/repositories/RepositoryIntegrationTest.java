package com.zonesoft.persons.repositories;

import static com.zonesoft.persons.data_generators.GeneratorCore.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.zonesoft.persons.models.Person;

import reactor.core.publisher.Mono;

@Testcontainers()
@DataMongoTest
class RepositoryIntegrationTest{
	private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryIntegrationTest.class);
	
	private static final String IMAGE_NAME = "mongo:6.0.2";

    private static final MongoDBContainer MONGODB_CONTAINER;
    static {
    	LOGGER.debug("From MONGODB_CONTAINER initialisation");
    	MONGODB_CONTAINER = new MongoDBContainer(DockerImageName.parse(IMAGE_NAME));
        MONGODB_CONTAINER.start();
    }

	
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {		
		registry.add("spring.data.mongodb.uri", MONGODB_CONTAINER::getReplicaSetUrl);  
	}
	
	@Autowired
	private PersonRepository personRepository;
	
	private Mono<Person> createAndInsertSinglePerson() {
		Person person = new Person(generateNickname(), generateFirstName(generateGender()), generateLastName());
		Mono<Person> createdPerson =  personRepository.insert(person);
		return createdPerson;
	}
	
	private List<Person>  createAndInsertPersons() {
		final int MAX_PERSONS = 7;
		final int MIN_PERSONS = 2;
		int numberOfPersons = generateRandomInt(MIN_PERSONS, MAX_PERSONS);
		List<Person> createdPersons = new ArrayList<>();
		for (int j=0; j < numberOfPersons; j++) {
			Person createdPerson = createAndInsertSinglePerson().block(); // Wait until person is created
			createdPersons.add(createdPerson);
		}
		return createdPersons;
	}
	
	@Test
	void testFindAll_returnsInsertedNumberOfPersons() {
		assertNotNull(personRepository);
		personRepository.deleteAll().block();
		List<Person> createdPersons = createAndInsertPersons();
		LOGGER.debug("createdPersons ={}", createdPersons);
		List<Person> findAllResults = personRepository.findAll().collectList().block();
		LOGGER.debug("testFindAll_returnsInsertedNumberOfPersons: results ={}", findAllResults);
		assertEquals(createdPersons.size(), findAllResults.size());
	}
	
	@Test
	void testFindAll_returnsNoResultsWhenNoRecordsInserted() {
		assertNotNull(personRepository);
		personRepository.deleteAll().block();
		List<Person> findAllResults = personRepository.findAll().collectList().block();
		LOGGER.debug("testFindAll_returnsNoResultsWhenNoRecordsInserted: results ={}", findAllResults);
		assertEquals(0, findAllResults.size());
	}
	
	@Test
	void test_FindById_GivenAValidId_ReturnsSingleValidPerson() {
		Person createdPerson = createAndInsertSinglePerson().block();
		Person findResult = personRepository.findById(createdPerson.getId()).block();
		assertNotNull(findResult);
		assertEquals(createdPerson.toString(), findResult.toString());
	}
	

}