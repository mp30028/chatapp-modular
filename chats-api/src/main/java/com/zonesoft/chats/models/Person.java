package com.zonesoft.chats.models;

import static com.zonesoft.utils.ToStringHelpers.*;


public class Person {
	
	private String id;


	private String moniker;
	private String firstname;
	private String lastname;
	
	public Person(String id, String moniker, String firstname, String lastname) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.moniker = moniker;
	}

	public Person(String moniker, String firstname, String lastname) {
		super();
		this.id = null;
		this.firstname = firstname;
		this.lastname = lastname;
		this.moniker = moniker;
	}
	
	public Person() {
		super();
		this.id = null;
		this.firstname = null;
		this.lastname = null;
		this.moniker = null;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getMoniker() {
		return moniker;
	}
	
	public void setMoniker(String moniker) {
		this.moniker = moniker;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		lb(sb);
			n(sb); t(sb); v(sb, "\"id\": "); qv(sb,this.id); c(sb);
			n(sb); t(sb); v(sb, "\"moniker\": "); qv(sb,this.moniker); c(sb);
			n(sb); t(sb); v(sb, "\"firstname\": "); qv(sb,this.firstname); c(sb);
			n(sb); t(sb); v(sb, "\"lastname\": "); qv(sb,this.lastname);
			n(sb);
		rb(sb);	
		
		return sb.toString();
	}

}
