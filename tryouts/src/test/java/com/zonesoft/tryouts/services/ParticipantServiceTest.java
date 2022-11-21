package com.zonesoft.tryouts.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.zonesoft.tryouts.data_generators.GenerateDummyData;
import com.zonesoft.tryouts.repositories.ParticipantRepository;
import com.zonesoft.tryouts.repositories.PersonRepository;

@SpringBootTest
class ParticipantServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParticipantServiceTest.class);
	
	private final ParticipantService service;
    
    @Autowired
    public ParticipantServiceTest(ParticipantService service) {
    	super();
    	this.service = service;
    	
    }
	
	@Test
	void testFindById() throws InterruptedException {
		assertNotNull(service);
		service.findById("6378a24c403c41204633715b")
		.subscribe((p) -> LOGGER.debug("participant found={}",p));
		Thread.sleep(10000);
	}

}
