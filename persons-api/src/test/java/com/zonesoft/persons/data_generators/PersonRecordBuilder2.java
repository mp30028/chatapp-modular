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
import com.zonesoft.utils.data_generators.IRecordBuilder;

public class PersonRecordBuilder2 implements IRecordBuilder<Person>{
	
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
	
	public PersonRecordBuilder2 id(String s){
		this.id = s;
		return this;
	}

	public PersonRecordBuilder2 moniker(String s){
		this.moniker = s;
		return this;
	}

	public PersonRecordBuilder2 firstname(String s){
		this.firstname = s;
		return this;
	}
	
	public PersonRecordBuilder2 lastname(String s){
		this.lastname = s;
		return this;
	}
	
	public PersonRecordBuilder2 otherNames(List<OtherName> l){
		this.otherNames = l;
		return this;
	}	
	
	public PersonRecordBuilder2 minOtherNames(int minimumNumberOfOtherNames){
		this.minOtherNames = minimumNumberOfOtherNames;
		return this;
	}	
	
	public PersonRecordBuilder2 maxOtherNames(int maximumNumberOfOtherNames){
		this.maxOtherNames = maximumNumberOfOtherNames;
		return this;
	}	
	
	public PersonRecordBuilder2 id(){
		this.id = generateUUID();
		return this;
	}

	public PersonRecordBuilder2 moniker(){
		this.moniker = generateNickname();
		return this;
	}

	public PersonRecordBuilder2 firstname(){
		this.firstname = generateFirstName(gender);
		return this;
	}
	
	public PersonRecordBuilder2 lastname(){
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
	
	public PersonRecordBuilder2 otherNames(int minimumNumberOfOtherNames, int maximumNumberOfOtherNames){
		this.minOtherNames = minimumNumberOfOtherNames;
		this.maxOtherNames = maximumNumberOfOtherNames;
		return otherNames();
	}
	
	public PersonRecordBuilder2 otherNames(){
		int numberOfOtherNames = generateRandomInt(minOtherNames, maxOtherNames);
		this.otherNames = new ArrayList<>();
		for (int j=0; j < numberOfOtherNames; j++) {
			this.otherNames.add(generateOtherName());	
		}
		return this;
	}
	
	public PersonRecordBuilder2 withDefaults() {
		return this.withDefaults(true);
	}
	
	public PersonRecordBuilder2 withDefaults(boolean withId) {
		return (withId) ? 
				this.id().moniker().firstname().lastname().otherNames(1,3) : 
				this.moniker().firstname().lastname().otherNames(1,3);
	}
	
	@Override
	public Person build() {
		Person person = new Person(id, moniker, firstname, lastname);
		if (Objects.nonNull(otherNames)) {
			for(OtherName name: otherNames) {
				person.otherNames().add(name);
			}
		}
		return person;
	}
}
