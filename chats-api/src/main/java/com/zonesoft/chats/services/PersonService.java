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