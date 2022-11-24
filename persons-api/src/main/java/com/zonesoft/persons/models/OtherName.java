package com.zonesoft.persons.models;

import static com.zonesoft.utils.helpers.ToStringBuilder.*;

import com.zonesoft.utils.helpers.ToStringBuilder;

public class OtherName {

	public enum OtherNameType{
		MIDDLE_NAME,
		NICK_NAME,
		ALIAS;
	}
	
	private String value;
	private OtherNameType nameType;
	
	public OtherName(String id, String value, OtherNameType nameType) {
		super();
		this.value = value;
		this.nameType = nameType;
	}

	public OtherName(String value, OtherNameType nameType) {
		super();
		this.value = value;
		this.nameType = nameType;
	}
	
	public OtherName() {
		super();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public OtherNameType getNameType() {
		return nameType;
	}

	public void setNameType(OtherNameType nameType) {
		this.nameType = nameType;
	}
	
	@Override
	public String toString() {		
		return new ToStringBuilder()
		.build(
				lBrace, newline,
					indent, key("value"), value(this.value), comma, newline,
					indent, key("name-type"), value(this.nameType), newline,
				rBrace
		);
	}
	
}
