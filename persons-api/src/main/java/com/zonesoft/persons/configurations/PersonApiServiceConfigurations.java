package com.zonesoft.persons.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class PersonApiServiceConfigurations {
	
	
    public PersonApiServiceConfigurations() {
		super();
	}

	@Value("${server.port}")
    private int serverPort;
    
	@Bean
    public int getServerPort() {
		return serverPort;
	}

	@Bean
	public WebFluxConfigurer corsConfigurer() {
		return new WebFluxConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:8080","http://localhost:3000" );
//				registry.addMapping("/**").allowedOrigins("http://localhost:3000");
			}
		};
	}
}
