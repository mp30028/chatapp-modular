package com.zonesoft.tryouts.data_generators;

import java.util.ArrayList;
import java.util.List;

import com.zonesoft.tryouts.models.OtherName;
import com.zonesoft.tryouts.models.OtherName.OtherNameType;
import com.zonesoft.tryouts.models.Person;
import static com.zonesoft.utils.data_generators.Generator.*;

public class PersonDataGenerator {
	private static final int MAX_PERSONS = 7;
	private static final int MIN_PERSONS = 1;
	private static final int MAX_OTHER_NAMES = 4;
	private static final int MIN_OTHER_NAMES = 0;
	
	public static OtherNameType generateOtherNameType() {
		int numberOfOtherNameTypes = OtherNameType.values().length;
		int selectedOtherNameType = generateRandomInt(0, numberOfOtherNameTypes-1);
		return OtherNameType.values()[selectedOtherNameType];
	}
	
	public static OtherName generateOtherName(Gender gender) {
		OtherNameType nameType = generateOtherNameType();
		return new OtherName(generateMiddleName(gender), nameType);
	}
	
	public static  Person generatePerson() {
		Gender gender = generateGender();
		Person person = new Person(generateNickname(), generateFirstName(gender), generateLastName());
		int numberOfOtherNames = generateRandomInt(MIN_OTHER_NAMES, MAX_OTHER_NAMES);
		for (int j=0; j < numberOfOtherNames; j++) {
			person.otherNames().add(generateOtherName(gender));	
		}		
		return person;
	}
	
	public static List<Person>  generatePersons() {
		int numberOfPersons = generateRandomInt(MIN_PERSONS, MAX_PERSONS);
		List<Person> persons = new ArrayList<>();
		for (int j=0; j < numberOfPersons; j++) {
			Person createdPerson = generatePerson();
			persons.add(createdPerson);
		}
		return persons;
	}
}
