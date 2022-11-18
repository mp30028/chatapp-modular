package com.zonesoft.tryouts.models;

import static com.zonesoft.utils.helpers.ToStringBuilder.*;

import java.util.ArrayList;
import java.util.List;

import com.zonesoft.utils.helpers.ToStringBuilder;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "persons")
public class Person {
	@Id private String id;
	private String moniker;
	private String firstname;
	private String lastname;
	private List<OtherName> otherNames = new ArrayList<>();

	public Person() {
		super();
	}

	public Person(String moniker, String firstname, String lastname) {
		super();
		this.setMoniker(moniker);
		this.setFirstname(firstname);
		this.setLastname(lastname);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMoniker() {
		return moniker;
	}

	public void setMoniker(String moniker) {
		this.moniker = moniker;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public List<OtherName> otherNames(){
		return this.otherNames;
	}
	
	List<OtherName> getOtherNames(){
		return this.otherNames;
	}
	
	void setOtherNames(List<OtherName> otherNames){
		this.otherNames = otherNames;
	}

	@Override
	public String toString() {		
		return new ToStringBuilder()
		.build(
				lBrace, newline,
					indent, key("id"), value(this.id), comma, newline,
					indent, key("moniker"), value(this.moniker), comma, newline,
					indent, key("firstname"), value(this.firstname), comma, newline,
					indent, key("lastname"), value(this.lastname), comma, newline,
					indent, key("other-names"), objectValue(this.otherNames), newline,
				rBrace
		);
	}
	
}