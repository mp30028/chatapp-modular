package com.zonesoft.tryouts.repositories;

import static com.zonesoft.tryouts.data_generators.PersonDataGenerator.*;
import static com.zonesoft.utils.data_generators.Generator.generateRandomInt;

import static org.junit.jupiter.api.Assertions.*;

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

import com.zonesoft.tryouts.models.Person;

@Testcontainers()
@DataMongoTest
class PersonRepositoryTest{
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonRepositoryTest.class);
	
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

	
	private Person  createAndInsertSinglePerson() {
		Person generatedPerson = generatePerson();
		return personRepository.insert(generatedPerson).block();
	}
	
	private List<Person>  createAndInsertPersons() {
		List<Person> generatedPersons = generatePersons();
		return personRepository.insert(generatedPersons).collectList().block();
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
	void testFindAll_returnsOneResultsWhenOneRecordsInserted() {
		assertNotNull(personRepository);
		personRepository.deleteAll().block();
		createAndInsertSinglePerson();
		List<Person> findAllResults = personRepository.findAll().collectList().block();
		LOGGER.debug("testFindAll_returnsOneResultsWhenOneRecordsInserted: results ={}", findAllResults);
		assertEquals(1, findAllResults.size());
	}
	
	@Test
	void testFindAll_returnsManyResultsWhenManyRecordsInserted() {
		assertNotNull(personRepository);
		personRepository.deleteAll().block();
		List<Person> createdPersons = createAndInsertPersons();
		List<Person> findAllResults = personRepository.findAll().collectList().block();
		LOGGER.debug("testFindAll_returnsManyResultsWhenManyRecordsInserted: results ={}", findAllResults);
		assertEquals(createdPersons.size(), findAllResults.size());
	}
	
	@Test
	void test_FindById_GivenAValidId_ReturnsSingleValidPerson() {
		Person createdPerson = createAndInsertSinglePerson();
		Person findResult = personRepository.findById(createdPerson.getId()).block();
		assertNotNull(findResult);
		assertEquals(createdPerson.toString(), findResult.toString());
	}
	
	@Test
	void test_FindByMoniker_GivenAValidMoniker_ReturnsValidPersons() {
		personRepository.deleteAll().block();
		List<Person> createdPersons = createAndInsertPersons();
		int selectedIndex = generateRandomInt(0, createdPersons.size()-1);
		String monikerToFind = createdPersons.get(selectedIndex).getMoniker();
		List<Person> searchResults = personRepository.findByMoniker(monikerToFind).collectList().block();
		LOGGER.debug("test_FindByMoniker_GivenAValidMoniker_ReturnsValidPersons: monikerToFind = {}, searchResults = {}",monikerToFind, searchResults);
	}
	

}