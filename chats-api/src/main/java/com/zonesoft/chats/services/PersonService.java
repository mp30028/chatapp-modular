package com.zonesoft.chats.services;

import static com.zonesoft.utils.ToStringHelpers.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zonesoft.chats.configurations.PersonsApiClientConfigurations;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
	
	@Autowired private PersonsApiClientConfigurations configs;
	
	public Mono<String> findByMoniker(String moniker){
		return null;
		
	}
	
	public Flux<String> fetchByMoniker(String moniker){
		Flux<String> response = configs.apiClient()
			.get()
			.uri(uriBuilder -> uriBuilder.path(configs.path()).queryParam("moniker", moniker).build())
			.retrieve()
			.bodyToFlux(Person.class)
			.map(p -> {LOGGER.debug("fetchByMoniker: result={}",p); return p.getId();});
		return response;
	}

	public Flux<String> fetchAll(){
		Flux<String> response = configs.apiClient()
			.get()
			.uri(uriBuilder -> uriBuilder.path(configs.path()).build())
			.retrieve()
			.bodyToFlux(Person.class)
			.map(p -> {LOGGER.debug("fetchAll: result={}",p); return p.getId();});
		return response;
	}
	
}

class Person{
	private String id;
	private String moniker;
	private String firstname;
	private String lastname;

	public Person(String id, String moniker, String firstname, String lastname) {
		super();
		this.id =id;
		this.moniker = moniker;
		this.firstname = firstname;
		this.lastname = lastname;
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
