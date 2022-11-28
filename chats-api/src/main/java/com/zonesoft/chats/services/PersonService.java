package com.zonesoft.chats.services;

import java.util.List;

//import static com.zonesoft.utils.ToStringHelpers.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayway.jsonpath.JsonPath;
import com.zonesoft.chats.configurations.PersonsApiClientConfigurations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
public class PersonService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
	private PersonsApiClientConfigurations configs;
	
	@Autowired
	public PersonService(PersonsApiClientConfigurations configs) {
		super();
		this.configs = configs;
	}
	
//	public Flux<String> fetchByMoniker(String moniker){
//		Flux<String> response = configs.getApiClient()
//			.get()
//			.uri(uriBuilder -> uriBuilder.path(configs.getPath()).queryParam("moniker", moniker).build())
//			.retrieve()
//			.bodyToFlux(String.class)
//			.map(s -> {LOGGER.debug("fetchByMoniker: result(json)={}",s); return s;});
//		return response;
//	}

//	public Mono<List<String>> fetchAll(){
//		Mono<List<String>> response = configs.getApiClient()
//			.get()
//			.uri(uriBuilder -> uriBuilder.path(configs.getPath()).build())
//			.retrieve()
//			.bodyToMono(String.class)
//			.map(s -> {LOGGER.debug("fetchByMoniker: result(json)={}",s); return s;})
//			.map(s -> {List<String> ids = JsonPath.parse(s).read("$[*].id"); return ids;});
//		return response;
//	}
	
	public Mono<List<Tuple2<String, String>>> fetchByMoniker(String moniker){
		Mono<List<Tuple2<String, String>>> response = configs.getApiClient()
			.get()
			.uri(uriBuilder -> uriBuilder.path(configs.getPath()).queryParam("moniker", moniker).build())
			.retrieve()
			.bodyToMono(String.class)
			.map(s -> {LOGGER.debug("fetchByMoniker: result(json)={}",s); return s;})
			.map(s -> {List<Tuple2<String, String>> parseResult = JsonPath.parse(s).read("$[*]['id','moniker']"); return parseResult;});
		return response;
	}
	
	public Mono<List<Tuple2<String, String>>> fetchAll(){
		Mono<List<Tuple2<String, String>>> response = configs.getApiClient()
			.get()
			.uri(uriBuilder -> uriBuilder.path(configs.getPath()).build())
			.retrieve()
			.bodyToMono(String.class)
			.map(s -> {LOGGER.debug("fetchAll: result(json)={}",s); return s;})
			.map(s -> {List<Tuple2<String, String>> parseResult = JsonPath.parse(s).read("$[*]['id','moniker']"); return parseResult;});
		return response;
	}	
	
}