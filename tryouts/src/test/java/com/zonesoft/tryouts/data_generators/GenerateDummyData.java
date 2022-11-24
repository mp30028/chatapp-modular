package com.zonesoft.tryouts.data_generators;

import com.zonesoft.tryouts.repositories.ParticipantRepository;
import com.zonesoft.tryouts.repositories.PersonRepository;

import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class GenerateDummyData {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GenerateDummyData.class);
	
	private final ParticipantRepository participantsRepository;
	private final PersonRepository personRepository;
    
	@Value("${com.zonesoft.tryouts.regenerate-data}")
    private boolean regenerateData = false;
    
    @Autowired
    public GenerateDummyData(ParticipantRepository participantsRepository, PersonRepository personRepository) {
    	super();
    	this.participantsRepository = participantsRepository;
		this.personRepository = personRepository;
    	
    }
	
	@Test
	void deleteAndCreateDummyData() {
		if (regenerateData){
			ParticipantDataGenerator generator = new ParticipantDataGenerator(participantsRepository, personRepository);
			generator.deleteAllParticipantsInDb();
			String result = generator.generateParticipantsToDb();
			LOGGER.debug("Data regenerated. regenerateData = {}, result={}", regenerateData, result);
		}else {
			LOGGER.debug("Data not regenerated because regenerateData = {}", regenerateData);
		}
	}

}
