package com.zonesoft.tryouts.repositories;

import static com.zonesoft.utils.data_generators.Generator.generateFirstName;
import static com.zonesoft.utils.data_generators.Generator.generateGender;
import static com.zonesoft.utils.data_generators.Generator.generateLastName;
import static com.zonesoft.utils.data_generators.Generator.generateMiddleName;
import static com.zonesoft.utils.data_generators.Generator.generateNickname;
import static com.zonesoft.utils.data_generators.Generator.generateRandomInt;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.zonesoft.tryouts.models.Dummy;
import com.zonesoft.tryouts.models.OtherName;
import com.zonesoft.tryouts.models.OtherName.OtherNameType;
import com.zonesoft.utils.data_generators.Generator.Gender;

@SpringBootTest
class DummyRepositoryIntegrationTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DummyRepositoryIntegrationTest.class);
	
	@Autowired DummyRepository repository;
	@Autowired private ReactiveMongoTemplate mongoTemplate;
	private String dummiesCollectionName = "dummies";
	
	@Test
	@Tag("with-dummy")
	void testFindAll_givenCollectionDoesNotExist_thenReturnsEmpty() throws InterruptedException {
		assertNotNull(repository);
		assertNotNull(mongoTemplate);
		Dummy teapot = new Dummy("TEAPEE", "I. AM. A", "TEAPOT");
		if (dummiesCollectionExists()) dropDummiesCollection();
		Dummy returnValue = repository
								.findAll()
								.defaultIfEmpty(teapot)
								.map((d) -> {LOGGER.debug("DummyRepositoryIntegrationTest.testFindAll_givenCollectionDoesNotExist_thenReturnsEmpty: flux emitted Dummy={}",d); return d;})
								.collectList()
								.map(l -> l.get(0))
								.block();
		assertEquals(teapot, returnValue);
		LOGGER.info("Finished testFindAll");
	}
	
	@Test
	@Tag("with-dummy")
	void testFindAll_givenCollectionExistsButNoData_thenReturnsEmpty() throws InterruptedException {
		assertNotNull(repository);
		assertNotNull(mongoTemplate);
		Dummy teapot = new Dummy("TEAPEE", "I. AM. A", "TEAPOT");
		if (!dummiesCollectionExists()) { 
			createDummiesCollection();
		}
		Dummy returnValue = repository
								.findAll()
								.defaultIfEmpty(teapot)
								.map((d) -> {LOGGER.debug("DummyRepositoryIntegrationTest.testFindAll_givenCollectionExistsButNoData_thenReturnsEmpty: flux emitted Dummy={}",d); return d;})
								.collectList()
								.map(l -> l.get(0))
								.block();
		assertEquals(teapot, returnValue);
		LOGGER.info("Finished testFindAll");
	}
	
	
	private void createDummiesCollection() {
		mongoTemplate.createCollection(dummiesCollectionName).block();
	}

	private void dropDummiesCollection() {
		mongoTemplate.dropCollection(dummiesCollectionName).block();
	}



	private static final int MAX_DUMMIES = 7;
	private static final int MIN_DUMMIES = 1;
	private static final int MAX_OTHER_NAMES = 4;
	private static final int MIN_OTHER_NAMES = 0;
	
	private boolean dummiesCollectionExists() {
		return this.mongoTemplate.collectionExists(dummiesCollectionName).block();
	}
	
	private OtherNameType generateOtherNameType() {
		int numberOfOtherNameTypes = OtherNameType.values().length;
		int selectedOtherNameType = generateRandomInt(0, numberOfOtherNameTypes-1);
		return OtherNameType.values()[selectedOtherNameType];
	}
	
	private OtherName generateOtherName(Gender gender) {
		OtherNameType nameType = generateOtherNameType();
		return new OtherName(generateMiddleName(gender), nameType);
	}
	
	private  Dummy generateDummy() {
		Gender gender = generateGender();
		Dummy dummy = new Dummy(generateNickname(), generateFirstName(gender), generateLastName());
		int numberOfOtherNames = generateRandomInt(MIN_OTHER_NAMES, MAX_OTHER_NAMES);
		for (int j=0; j < numberOfOtherNames; j++) {
			dummy.otherNames().add(generateOtherName(gender));	
		}		
		return dummy;
	}
	
	private List<Dummy>  generateDummies() {
		int numberOfDummies = generateRandomInt(MIN_DUMMIES, MAX_DUMMIES);
		List<Dummy> dummies = new ArrayList<>();
		for (int j=0; j < numberOfDummies; j++) {
			Dummy createdPerson = generateDummy();
			dummies.add(createdPerson);
		}
		return dummies;
	}

}
