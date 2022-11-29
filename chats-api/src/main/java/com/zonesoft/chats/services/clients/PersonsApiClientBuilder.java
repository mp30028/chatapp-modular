package com.zonesoft.chats.services.clients;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Configuration
public class PersonsApiClientBuilder {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonsApiClientBuilder.class);
	
	private volatile static WebClient webClientInstance = null;
    
    @Value("${com.zonesoft.persons.webclient.protocol}")
    private String protocol;
    
    @Value("${com.zonesoft.persons.webclient.domain}")
    private String domain;
    
    @Value("${com.zonesoft.persons.webclient.port}")
    private String port;
    
    @Value("${com.zonesoft.persons.webclient.path}")
    private String path;
    
    @Value("${com.zonesoft.persons.webclient.client-name}")
    private String clientName;
    
    @Value("${com.zonesoft.persons.webclient.client-type}")
    private String clientType ;

    public PersonsApiClientBuilder protocol(String newValue) {
    	Consumer<String> c = nv -> this.protocol = nv;
    	safeUpdate(c, newValue);
    	return this;
    }
    
    public PersonsApiClientBuilder domain(String newValue) {
    	Consumer<String> c = nv -> this.domain = nv;
    	safeUpdate(c, newValue);
    	return this;
    }
    
    public PersonsApiClientBuilder port(String newValue) {
    	Consumer<String> c = nv -> this.port = nv;
    	safeUpdate(c, newValue);
    	return this;
    }
    
    public PersonsApiClientBuilder path(String newValue) {
    	Consumer<String> c = nv -> this.path = nv;
    	safeUpdate(c, newValue);
    	return this;
    }
    
    public PersonsApiClientBuilder clientName(String newValue) {
    	Consumer<String> c = nv -> this.clientName = nv;
    	safeUpdate(c, newValue);
    	return this;
    }
    
    public PersonsApiClientBuilder clientType(String newValue) {
    	Consumer<String> c = nv -> this.clientType = nv;
    	safeUpdate(c, newValue);
    	return this;
    }
    
    public String getPath() {
    	return this.path;
    }
    
//    public String fetchByMonikerUri(String moniker) {    		
//    	return this.path + "%3Fmoniker=" + moniker;
//    }
//    
//    public String fetchAllUri() {
//    	URI uri = UriComponentsBuilder.fromPath(this.path).build().toUri();	
//    	LOGGER.debug("fetchAllUri: uri={}",uri.toString());
//    	return uri.getPath();
//    }
    
    private void safeUpdate(Consumer<String> updater, String newValue) {
    	if (Objects.isNull(webClientInstance)) {
    		synchronized(PersonsApiClientBuilder.class) {
    			if (Objects.isNull(webClientInstance)) {
    				updater.accept(newValue);
    			}
    		}
    	}else {
    		updater.accept(newValue);
    	}
    }
    
    public PersonsApiClientBuilder reset() {
		if (Objects.nonNull(webClientInstance)) {
			synchronized (PersonsApiClientBuilder.class) {
				webClientInstance = null;
			}
		}
		return this;
	}

	public WebClient build() {
		if (Objects.isNull(webClientInstance)) {
			synchronized (PersonsApiClientBuilder.class) {
				if (Objects.isNull(webClientInstance)) {
					HttpClient httpClient = HttpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2_000) // millis
							.doOnConnected(connection -> connection.addHandlerLast(new ReadTimeoutHandler(2)) // seconds
									.addHandlerLast(new WriteTimeoutHandler(2))); // seconds

					PersonsApiClientBuilder.webClientInstance = WebClient.builder()
							.baseUrl(this.protocol + "://" + this.domain + ":" + this.port)
							.clientConnector(new ReactorClientHttpConnector(httpClient))
							.defaultCookie("client-name", clientName).defaultCookie("client-type", clientType)
							.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//							.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
							.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE).filter(logRequest())
							.filter(logResponse()).build();
				}
			}
		}
		return webClientInstance;
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
			clientResponse.headers().asHttpHeaders()
					.forEach((name, values) -> values.forEach(value -> LOGGER.debug("{}={}", name, value)));
			return Mono.just(clientResponse);
		});
	}

	private void logHeader(String name, List<String> values) {
		values.forEach(value -> LOGGER.debug("{}={}", name, value));
	}  
}



