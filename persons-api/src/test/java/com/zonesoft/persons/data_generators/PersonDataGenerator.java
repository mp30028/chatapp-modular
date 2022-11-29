package com.zonesoft.persons.data_generators;

import static com.zonesoft.utils.data_generators.Generator.generateFirstName;
import static com.zonesoft.utils.data_generators.Generator.generateGender;
import static com.zonesoft.utils.data_generators.Generator.generateLastName;
import static com.zonesoft.utils.data_generators.Generator.generateMiddleName;
import static com.zonesoft.utils.data_generators.Generator.generateNickname;
import static com.zonesoft.utils.data_generators.Generator.generateRandomInt;
import static com.zonesoft.utils.data_generators.Generator.generateUUID;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.zonesoft.persons.models.OtherName;
import com.zonesoft.persons.models.Person;
import com.zonesoft.persons.models.OtherName.OtherNameType;
import com.zonesoft.utils.data_generators.Generator.Gender;

public class PersonDataGenerator {
	
	private static final int MIN_OTHER_NAMES_DEFAULT = 0;
	private static final int MAX_OTHER_NAMES_DEFAULT = 3;
	
	private String id = null;
	private String moniker = null;
	private String firstname = null;
	private String lastname = null;
	private List<OtherName> otherNames = null;
	private static final Gender gender = generateGender();
	private int minOtherNames = MIN_OTHER_NAMES_DEFAULT;
	private int maxOtherNames = MAX_OTHER_NAMES_DEFAULT; 
	
	public PersonDataGenerator id(String s){
		this.id = s;
		return this;
	}

	public PersonDataGenerator moniker(String s){
		this.moniker = s;
		return this;
	}

	public PersonDataGenerator firstname(String s){
		this.firstname = s;
		return this;
	}
	
	public PersonDataGenerator lastname(String s){
		this.lastname = s;
		return this;
	}
	
	public PersonDataGenerator otherNames(List<OtherName> l){
		this.otherNames = l;
		return this;
	}	
	
	public PersonDataGenerator minOtherNames(int minimumNumberOfOtherNames){
		this.minOtherNames = minimumNumberOfOtherNames;
		return this;
	}	
	
	public PersonDataGenerator maxOtherNames(int maximumNumberOfOtherNames){
		this.maxOtherNames = maximumNumberOfOtherNames;
		return this;
	}	
	
	public PersonDataGenerator id(){
		this.id = generateUUID();
		return this;
	}

	public PersonDataGenerator moniker(){
		this.moniker = generateNickname();
		return this;
	}

	public PersonDataGenerator firstname(){
		this.firstname = generateFirstName(gender);
		return this;
	}
	
	public PersonDataGenerator lastname(){
		this.lastname = generateLastName();
		return this;
	}
	
	private OtherNameType generateOtherNameType() {
		int numberOfOtherNameTypes = OtherNameType.values().length;
		int selectedOtherNameType = generateRandomInt(0, numberOfOtherNameTypes-1);
		return OtherNameType.values()[selectedOtherNameType];
	}
	
	private OtherName generateOtherName() {
		return new OtherName(generateUUID(), generateMiddleName(gender), generateOtherNameType());
	}
	
	public PersonDataGenerator otherNames(int minimumNumberOfOtherNames, int maximumNumberOfOtherNames){
		this.minOtherNames = minimumNumberOfOtherNames;
		this.maxOtherNames = maximumNumberOfOtherNames;
		return otherNames();
	}
	
	public PersonDataGenerator otherNames(){
		int numberOfOtherNames = generateRandomInt(minOtherNames, maxOtherNames);
		this.otherNames = new ArrayList<>();
		for (int j=0; j < numberOfOtherNames; j++) {
			this.otherNames.add(generateOtherName());	
		}
		return this;
	}
	
	public PersonDataGenerator withDefaults() {
		this.id().moniker().firstname().lastname().otherNames(1,3);
		return this;
	}
	
	public Person generate() {
		Person person = new Person(id, moniker, firstname, lastname);
		if (Objects.nonNull(otherNames)) {
			for(OtherName name: otherNames) {
				person.otherNames().add(name);
			}
		}
		return person;
	}
}
