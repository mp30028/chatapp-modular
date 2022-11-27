package com.zonesoft.persons.data_generators;

import static com.zonesoft.utils.data_generators.Generator.generateRandomInt;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.zonesoft.persons.models.Person;

public class PersonsDataGenerator {
	private static final int MIN_PERSONS_DEFAULT = 2;
	private static final int MAX_PERSONS_DEFAULT = 10;
	private static final boolean AUTO_GENERATE_ID_DEFAULT = true;
	private int minPersons = MIN_PERSONS_DEFAULT;
	private int maxPersons = MAX_PERSONS_DEFAULT;
	private boolean autoGenerateId = AUTO_GENERATE_ID_DEFAULT;
	private String[] providedIds = null;
	
	public PersonsDataGenerator minPersons(int minimumNumberOfPersons) {
		minPersons = minimumNumberOfPersons;
		return this;
	}
	
	public PersonsDataGenerator maxPersons(int maximumNumberOfPersons) {
		this.maxPersons = maximumNumberOfPersons;
		return this;
	}
	
	public PersonsDataGenerator id(boolean autoGenerateId) {
		this.autoGenerateId = autoGenerateId;
		return this;
	}

	public PersonsDataGenerator id(String ...ids) {
		this.providedIds = ids;
		return this;
	}
	
	public List<Person> generate(){
		List<Person> persons = null;
		if (Objects.isNull(providedIds)) {
			persons = generatePersons();
		}else {
			persons = generatePersonsFromProvidedIds();
		}		
		return persons;
	}

	private List<Person> generatePersonsFromProvidedIds() {
		int listSize = (providedIds.length <= maxPersons)? providedIds.length : maxPersons;
		List<Person> persons = new ArrayList<>();		
		for(int j=0; j < listSize ; j++) {
			PersonDataGenerator generator = new PersonDataGenerator();
			Person person = generator.id(providedIds[j]).moniker().firstname().lastname().otherNames().generate();
			persons.add(person);
		}
		return persons;
	}

	private List<Person> generatePersons() {
		int listSize = generateRandomInt(minPersons, maxPersons);
		List<Person> persons = new ArrayList<>();		
		for(int j=0; j < listSize ; j++) {
			PersonDataGenerator generator = new PersonDataGenerator();
			if (autoGenerateId) {
				generator.id();
			}
			Person person = generator.moniker().firstname().lastname().otherNames().generate();
			persons.add(person);
		}
		return persons;
	}
}
