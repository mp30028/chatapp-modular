package com.zonesoft.chats.configurations;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
	
	private static WebClient webClientInstance = null;
    
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

    private static void resetWebClientInstance() {
		if (Objects.nonNull(webClientInstance)) {
			synchronized(PersonsApiClientConfigurations.class) {
				webClientInstance = null;
			}
		}
    }
    
	public int getPort() {
		return Integer.parseInt(port);
	}

	public void setPort(int p) {
		this.port = Integer.toString(p);
		resetWebClientInstance();
	}
    
    
    public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
		resetWebClientInstance();
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
		resetWebClientInstance();
	}
	
    public String getPath() {
    	return path;
    }
	
	public void setPath(String path) {
		this.path = path;
	}

	public String getBaseUrl() {
    	return this.protocol + "://" + this.domain + ":" + this.port;
    }
    
	 
	public WebClient getApiClient() {
		if (Objects.isNull(webClientInstance)) {
			synchronized(PersonsApiClientConfigurations.class) {
				if (Objects.isNull(webClientInstance)) {
				  HttpClient httpClient = HttpClient.create()
				    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2_000) // millis
				    .doOnConnected(connection ->
				      connection
				        .addHandlerLast(new ReadTimeoutHandler(2)) // seconds
				        .addHandlerLast(new WriteTimeoutHandler(2))); //seconds
			
				  PersonsApiClientConfigurations.webClientInstance = WebClient.builder()
				    .baseUrl(getBaseUrl())
				    .clientConnector(new ReactorClientHttpConnector(httpClient))
				    .defaultCookie("client-name", clientName)
				    .defaultCookie("client-type", clientType)
				    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				    .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				    .filter(logRequest())
				    .filter(logResponse())
				    .build();
				}
			}
		}
		return PersonsApiClientConfigurations.webClientInstance;
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



