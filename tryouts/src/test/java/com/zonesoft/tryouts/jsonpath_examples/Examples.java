package com.zonesoft.tryouts.jsonpath_examples;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import reactor.util.function.Tuple2;

public class Examples {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Examples.class);
	
	private String jsonString = "[\r\n"
			+ "    {\r\n"
			+ "    \"firstname\": \"Gillian\",\r\n"
			+ "    \"id\": \"b575baf9-8430-4c58-a361-04ad17bac353\",\r\n"
			+ "    \"lastname\": \"WILSON\",\r\n"
			+ "    \"moniker\": \"Scara\"\r\n"
			+ "    },\r\n"
			+ "    {\r\n"
			+ "    \"firstname\": \"Craig\",\r\n"
			+ "    \"id\": \"62616753-4d06-499e-8816-4ccdead06533\",\r\n"
			+ "    \"lastname\": \"PATERSON\",\r\n"
			+ "    \"moniker\": \"Nosey\"\r\n"
			+ "    }\r\n"
			+ "]";
	
	@Test
	void runExample01(){
		List<String> ids = JsonPath.parse(jsonString).read("$[*].id");
		LOGGER.debug("Example01 - ids={}", ids);
	}
	
	@Test
	void runExample02(){
		Object document = Configuration.defaultConfiguration().jsonProvider().parse(jsonString);
		List<Tuple2<String, String>> result = JsonPath.read(document, "$[*]['id','moniker']");
		LOGGER.debug("Example02 - result={}", result);
	}

}
