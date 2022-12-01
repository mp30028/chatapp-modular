package com.zonesoft.chats.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.jayway.jsonpath.JsonPath;
import com.zonesoft.chats.services.clients.PersonsApiClientBuilder;

import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
public class PersonService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
	private PersonsApiClientBuilder builder;
	
	@Autowired
	public PersonService(PersonsApiClientBuilder builder) {
		super();
		this.builder = builder;
	}
	
	public Mono<List<Tuple2<String, String>>> fetchByMoniker(String moniker){
		Mono<List<Tuple2<String, String>>> response = builder.build()
			.get()
			.uri(uriBuilder -> {
				LOGGER.debug("fetchByMoniker: path= {}", builder.getPath());
				return uriBuilder.path(builder.getPath()).queryParam("moniker", moniker).build();
			})
			.retrieve()
			.bodyToMono(String.class)
			.map(s -> {LOGGER.debug("fetchByMoniker: result(json)={}",s); return s;})
			.map(s -> {List<Tuple2<String, String>> parseResult = JsonPath.parse(s).read("$[*]['id','moniker']"); return parseResult;});
		return response;
	}
	
	public Mono<List<Tuple2<String, String>>> fetchAll(){
		WebClient client = builder.build();
		Mono<List<Tuple2<String, String>>> response = client
			.get()
			.uri(uriBuilder -> uriBuilder.path(builder.getPath()).build())
			.retrieve()
			.bodyToMono(String.class)
			.map(s -> {LOGGER.debug("fetchAll: result(json)={}",s); return s;})
			.map(s -> {List<Tuple2<String, String>> parseResult = JsonPath.parse(s).read("$[*]['id','moniker']"); return parseResult;});
		return response;
	}	
}