package com.zonesoft.participants.models;

import static com.zonesoft.utils.helpers.ToStringBuilder.*;

import com.zonesoft.utils.helpers.ToStringBuilder;

public class OtherName {

	public enum OtherNameType{
		MIDDLE_NAME,
		NICK_NAME,
		ALIAS;
	}
	
	private String id;
	private String value;
	private OtherNameType nameType;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public OtherName(String id, String value, OtherNameType nameType) {
		super();
		this.id = id;
		this.value = value;
		this.nameType = nameType;
	}

	public OtherName(String value, OtherNameType nameType) {
		super();
		this.value = value;
		this.nameType = nameType;
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
					indent, key("other-name-id"), value(this.id), comma, newline,
					indent, key("value"), value(this.value), comma, newline,
					indent, key("other-name-type"), value(this.nameType), newline,
				rBrace
		);
	}
	
}
