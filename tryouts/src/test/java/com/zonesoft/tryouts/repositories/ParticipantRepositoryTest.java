package com.zonesoft.tryouts.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

import com.zonesoft.tryouts.models.Participant;
import com.zonesoft.tryouts.models.Person;
import static com.zonesoft.tryouts.data_generators.ParticipantDataGenerator.*;
import static com.zonesoft.tryouts.data_generators.PersonDataGenerator.generatePersons;

//@Testcontainers
//@DataMongoTest
//@ContextConfiguration(classes = {MongoConfig.class, DateToOffsetDateTime.class, OffsetDateTimeToDate.class })
@SpringBootTest
class ParticipantRepositoryTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ParticipantRepositoryTest.class);
	
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
	
	@Autowired private ParticipantRepository participantRepository;
	@Autowired private PersonRepository personRepository;
	
	@Test
	void test_FindAllParticipants() {
		assertNotNull(participantRepository);
		List<Person> persons =  generatePersons();
		personRepository.insert(persons).collectList().block();
//		LOGGER.debug("test_FindAllParticipants - after insert: persons = {}", persons);
		List<Participant> participants = generateParticipants(persons);
		
		participantRepository.insert(participants).collectList().block();
		List<Participant> result = participantRepository.findAll().collectList().block();
//		assertEquals(participants.size(), result.size());
		LOGGER.debug("test_FindAllParticipants: result = {}", result);
	}

}
