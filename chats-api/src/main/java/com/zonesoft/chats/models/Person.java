package com.zonesoft.chats.models;


import static com.zonesoft.utils.helpers.ToStringBuilder.*;
import com.zonesoft.utils.helpers.ToStringBuilder;

import org.springframework.data.annotation.Id;



public class Person {
	@Id private String id;
	private String moniker;
	private String firstname;
	private String lastname;

	public Person() {
		super();
	}

	public Person(String id, String moniker, String firstname, String lastname) {
		super();
		this.setId(id);
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

	@Override
	public String toString() {		
		return new ToStringBuilder()
		.build(
				lBrace, newline,
					indent, key("id"), value(this.id), comma, newline,
					indent, key("moniker"), value(this.moniker), comma, newline,
					indent, key("firstname"), value(this.firstname), comma, newline,
					indent, key("lastname"), value(this.lastname), newline,
				rBrace
		);
	}

}