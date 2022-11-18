package com.zonesoft.chats.services;

//import static com.zonesoft.utils.ToStringHelpers.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zonesoft.chats.configurations.PersonsApiClientConfigurations;
import com.zonesoft.chats.models.Person;

import reactor.core.publisher.Flux;

@Service
public class PersonService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
	private PersonsApiClientConfigurations configs;
	
	@Autowired
	public PersonService(PersonsApiClientConfigurations configs) {
		super();
		this.configs = configs;
	}
	
	public Flux<String> fetchByMoniker(String moniker){
		Flux<String> response = configs.getApiClient()
			.get()
			.uri(uriBuilder -> uriBuilder.path(configs.getPath()).queryParam("moniker", moniker).build())
			.retrieve()
			.bodyToFlux(Person.class)
			.map(p -> {LOGGER.debug("fetchByMoniker: result={}",p); return p.getId();});
		return response;
	}

	public Flux<String> fetchAll(){
		Flux<String> response = configs.getApiClient()
			.get()
			.uri(uriBuilder -> uriBuilder.path(configs.getPath()).build())
			.retrieve()
			.bodyToFlux(Person.class)
			.map(p -> {LOGGER.debug("fetchAll: result={}",p); return p.getId();});
		return response;
	}
	
}

//class Person{
//	private Person data = new Person();
//
//	public Person() {
//		super();
//	}
//
//	public Person(String id, String moniker, String firstname, String lastname) {
//		super();
//		this.data.setId(id);
//		this.data.setMoniker(moniker);
//		this.data.setFirstname(firstname);
//		this.data.setLastname(lastname);
//	}
//
//	public String getId() {
//		return data.getId();
//	}
//
//	public void setId(String id) {
//		this.data.setId(id);
//	}
//
//	public String getMoniker() {
//		return data.getMoniker();
//	}
//
//	public void setMoniker(String moniker) {
//		this.data.setMoniker(moniker);
//	}
//
//	public String getFirstname() {
//		return data.getFirstname();
//	}
//
//	public void setFirstname(String firstname) {
//		this.data.setFirstname(firstname);
//	}
//
//	public String getLastname() {
//		return data.getLastname();
//	}
//
//	public void setLastname(String lastname) {
//		this.data.setLastname(lastname);
//	}
//
//	@Override
//	public String toString() {
//		StringBuffer sb = new StringBuffer();
//		lb(sb);
//			n(sb); t(sb); v(sb, "\"id\": "); qv(sb,this.data.getId()); c(sb);
//			n(sb); t(sb); v(sb, "\"moniker\": "); qv(sb,this.data.getMoniker()); c(sb);
//			n(sb); t(sb); v(sb, "\"firstname\": "); qv(sb,this.data.getFirstname()); c(sb);
//			n(sb); t(sb); v(sb, "\"lastname\": "); qv(sb,this.data.getLastname());
//			n(sb);
//		rb(sb);		
//		return sb.toString();
//	}
//	
//}
