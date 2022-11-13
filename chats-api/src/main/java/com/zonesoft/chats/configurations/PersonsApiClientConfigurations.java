package com.zonesoft.chats.configurations;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Configuration
public class PersonsApiClientConfigurations {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonsApiClientConfigurations.class);
    
    @Value("${com.zonesoft.persons.webclient.protocol}")
    private String PROTOCOL;
    
    @Value("${com.zonesoft.persons.webclient.domain}")
    private String DOMAIN;
    
    @Value("${com.zonesoft.persons.webclient.port}")
    private String PORT;
    
    @Value("${com.zonesoft.persons.webclient.path}")
    private String PATH;
    
    @Value("${com.zonesoft.persons.webclient.client-name}")
    private String CLIENT_NAME;
    
    @Value("${com.zonesoft.persons.webclient.client-type}")
    private String CLIENT_TYPE ;

    @Bean
    public String baseUrl() {
    	return PROTOCOL + "://" + DOMAIN + ":" + PORT;
    }
    
    @Bean
    public String path() {
    	return PATH;
    }
	
	@Bean
	public WebClient apiClient() {

	  HttpClient httpClient = HttpClient.create()
	    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2_000) // millis
	    .doOnConnected(connection ->
	      connection
	        .addHandlerLast(new ReadTimeoutHandler(2)) // seconds
	        .addHandlerLast(new WriteTimeoutHandler(2))); //seconds

	  return WebClient.builder()
	    .baseUrl(baseUrl())
	    .clientConnector(new ReactorClientHttpConnector(httpClient))
	    .defaultCookie("client-name", CLIENT_NAME)
	    .defaultCookie("client-type", CLIENT_TYPE)
	    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	    .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)

	    .filter(logRequest())
	    .filter(logResponse())
	    .build();
	}
	
	private ExchangeFilterFunction logRequest() {
		return (clientRequest, next) -> {
			LOGGER.debug("Request: {} {}", clientRequest.method(), clientRequest.url());
			LOGGER.debug("--- Http Headers: ---");
			clientRequest.headers().forEach(this::logHeader);
			LOGGER.debug("--- Http Cookies: ---");
			clientRequest.cookies().forEach(this::logHeader);
			return next.exchange(clientRequest);
		};
	}
	
	private ExchangeFilterFunction logResponse() {
		return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
			LOGGER.debug("Response-Code: {}", clientResponse.statusCode());
			clientResponse
				.headers()
				.asHttpHeaders()
				.forEach((name, values) -> values.forEach(value -> LOGGER.debug("{}={}", name, value)));
			return Mono.just(clientResponse);
		});
	}

	private void logHeader(String name, List<String> values) {
		values.forEach(value -> LOGGER.debug("{}={}", name, value));
	}
}



