package com.zonesoft.persons.handlers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.zonesoft.persons.models.Person;
import com.zonesoft.persons.services.PersonService;

import reactor.core.publisher.Mono;

@Component
public class PersonHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonHandler.class);
	private final PersonService service;
	
    @Autowired
	public PersonHandler(PersonService service) {
		super();
		this.service = service;
	}
    
    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), Person.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
    	LOGGER.debug("FROM PersonHandler.findById. id={}", request.pathVariable("id"));
        return service
                .findById(request.pathVariable("id"))
                .flatMap(p -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(p)
//                        .body(p, Person.class)
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> insert(ServerRequest request) {
        Mono<Person> personMono = request.bodyToMono(Person.class);

        return personMono
                .flatMap(p -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.insert(p), Person.class)
                );
    }

    public Mono<ServerResponse> update(ServerRequest request) {
//        String id = request.pathVariable("id");
        Mono<Person> personMono = request.bodyToMono(Person.class);
        return personMono
                .flatMap(p -> ServerResponse
                        .status(HttpStatus.ACCEPTED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.update(p), Person.class)
                );
    }

    public Mono<ServerResponse> deleteById(ServerRequest request){
      String id = request.pathVariable("id");
      return  service
    		  	.deleteById(id)
    		  	.then(
    		  			ServerResponse
    		  				.status(HttpStatus.ACCEPTED)
    		  				.body(Mono.empty(), Void.class)
    		  	);
    }
    
    public Mono<ServerResponse> findByMoniker(ServerRequest request) {
        Optional<String> moniker = request.queryParam("moniker");
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findByMoniker(moniker.get()), Person.class);
    }
    
}