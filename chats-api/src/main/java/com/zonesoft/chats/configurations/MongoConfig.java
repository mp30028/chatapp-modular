package com.zonesoft.chats.configurations;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.zonesoft.utils.converters.OffsetDateTimeToDate;
import com.zonesoft.utils.converters.DateToOffsetDateTime;

@Configuration
public class MongoConfig {

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new DateToOffsetDateTime(),
                new OffsetDateTimeToDate()
        ));
    }

}