package com.zonesoft.chats.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class PropertySourceConfigurations {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertiesResolver() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
	
}
