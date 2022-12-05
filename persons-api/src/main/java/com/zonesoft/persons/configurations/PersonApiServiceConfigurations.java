package com.zonesoft.persons.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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


}
