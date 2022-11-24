package com.zonesoft.tryouts.reactive_examples;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Examples {
	private static final Logger LOGGER= LoggerFactory.getLogger(Examples.class);
	

	
	@Test
	void runExample01() throws InterruptedException {
		LOGGER.debug("\n\nRunning Example 01. With declared Publisher and Subscriber");
		Flux<String> stringPublisher = Flux.just("one", "two", "three", "four");
		Consumer<String> stringSubscriber = ((s) -> LOGGER.debug("Example-01: string={}",s));
		stringPublisher.subscribe(stringSubscriber);
		Thread.sleep(1000); // prevent program exiting before the previous asynchronous step completes
	}
	
	
	@Test
	void runExample02() throws InterruptedException {
		LOGGER.debug("\n\nRunning Example 02. With inline Publisher and Subscriber");
		Flux.just("one", "two", "three", "four").subscribe((s) -> LOGGER.debug("Example-02: string={}",s));
		Thread.sleep(1000); // prevent program exiting before the previous asynchronous step completes
	}
	
	@Test
	void runExample03() throws InterruptedException {
		LOGGER.debug("\n\nRunning Example 03. With inline Publisher and <map> as intermediate subscriber/republisher");
		Flux.just("one", "two", "three", "four")
		.map((s)-> s.concat("-example-03"))
		.subscribe((s) -> LOGGER.debug("Example-03: string={}",s));
		Thread.sleep(1000); // prevent program exiting before the previous asynchronous step completes
	}
	
	@Test
	void runExample04() throws InterruptedException {
		LOGGER.debug("\n\nRunning Example 04. With <all> as subscriber");
		Flux.just("ex4-one", "ex4-two", "ex4-three", "ex4-four")
		.all(  (s)-> s.startsWith("ex4-"))
		.subscribe((b) -> LOGGER.debug("Example-04a: Expect result to be a boolean true. Actual result={}",b));
		Thread.sleep(1000); // prevent program exiting before the previous asynchronous step completes
		Flux.just("ex4-one", "ex4-two", "ex4-three", "four")
		.all( (s)-> s.startsWith("ex4-"))
		.subscribe((b) -> LOGGER.debug("Example-04b: Expect result to be a boolean false. Actual result={}",b));
		Thread.sleep(1000); // prevent program exiting before the previous asynchronous step completes
	}
	
	@Test
	void runExample05() throws InterruptedException {
		LOGGER.debug("\n\nRunning Example 05. Using the <as> operator to transform a Flux into a Mono");
		Function<Flux<String>, Mono<List<String>>> transformToMono = ((f) -> f.collectList());
		Flux.<String>just("one", "two", "three", "four")
		.as(transformToMono)
		.subscribe((l) -> LOGGER.debug("Example-05: Flux transformed to a single list={}",l));
		Thread.sleep(1000); // prevent program exiting before the previous asynchronous step completes
	}
	
	@Test
	void runExample06() throws InterruptedException {
		LOGGER.debug("\n\nRunning Example 06. Using the <as> operator to transform a Flux into a Mono but with a more complex example");
		Function<String, WordDigitPair> transformToPair = ((s) -> new WordDigitPair(s));
		Function<Flux<String>, Mono<List<WordDigitPair>>> transformToMono = ((f) -> f.map(transformToPair).collectList()) ;
		
		Flux.<String>just("one", "two", "three", "four")
		.as(transformToMono)
		.subscribe((l) -> LOGGER.debug("Example-06: Flux transformed to a single list of WordDigitPair elements={}",l));
		Thread.sleep(1000); // prevent program exiting before the previous asynchronous step completes
	}
	
	class WordDigitPair{
		private int digit;
		private String word;
		
		public WordDigitPair(String word) {
			super();
			this.word = word;
			switch (word) {
			case "one":this.digit = 1;break;
			case "two":this.digit = 2;break;
			case "three":this.digit = 3;break;
			case "four":this.digit = 4;break;
			default: this.digit=-1; break;
			}
		}

		public int getDigit() {
			return digit;
		}

		public void setDigit(int digit) {
			this.digit = digit;
		}

		public String getWord() {
			return word;
		}

		public void setWord(String word) {
			this.word = word;
		}
		
		@Override
		public String toString() {
			return "word=" + this.word + ". digit=" + this.digit ;
		}
	}
	
	@Test
	void runExample07() throws InterruptedException {
		LOGGER.debug("\n\nRunning Example 07. Using the <map> operator to transform elements of a Flux ");
		Function<String, WordDigitPair> transformToPair = ((s) -> new WordDigitPair(s));
		Flux.<String>just("one", "two", "three", "four")
		.map(transformToPair)
		.subscribe((p) -> LOGGER.debug("Example-07: Transformed value of WordDigitPair={}",p));
		Thread.sleep(1000); // prevent program exiting before the previous asynchronous step completes
	}
	
	@Test
	void runExample08() throws InterruptedException {
		LOGGER.debug("\n\nRunning Example 08. Using the <map> operator to transform elements of a Flux ");
		String[] inputs = {"one.two", "three.four.five", "six", "seven.eight", "nine", "zero"};
		String delimiter = "\\.";
		Function<String, String> splitter = ((s) -> s.split(delimiter)[0]);
		
		LOGGER.debug("Example-08 using <map>: Number of elements input= {}",inputs.length);
		Flux.just(inputs)
		.map(splitter)
		.map((s) -> {LOGGER.debug("Example-08 using <map>: Transformed value = {}",s); return s;})
		.count()
		.subscribe((c) -> LOGGER.debug("Example-08 using <map>: Number of elements output= {}",c));
		Thread.sleep(1000); // prevent program exiting before the previous asynchronous step completes
	}
	
	@Test
	void runExample09() throws InterruptedException {
		LOGGER.debug("\n\nRunning Example 09. Using the <flatMap> operator to transform elements of a Flux ");
		String[] inputs = {"one.two", "three.four.five", "six", "seven.eight", "nine", "zero"};
		String delimiter = "\\.";
		Function<String, Flux<String>> splitter = ((s) -> Flux.just(s.split(delimiter)));
		
		LOGGER.debug("Example-09 using <flatMap>: Number of elements input= {}",inputs.length);
		Flux.just(inputs)
		.flatMap(splitter)
		.map((s) -> {LOGGER.debug("Example-09 using <flatMap>: Transformed value = {}",s); return s;})
		.count()
		.subscribe((c) -> LOGGER.debug("Example-09 using <flatMap>: Number of elements output= {}",c));
		Thread.sleep(1000); // prevent program exiting before the previous asynchronous step completes
	}
	
	
	@Test
	void runExample10() throws InterruptedException {
		LOGGER.debug("\n\nRunning Example 10. Using the <then> operator ");
		String[] inputs = {"one.two", "three.four.five", "six", "seven.eight", "nine", "zero"};
		String[] secondList = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen"};
		String delimiter = "\\.";
		Function<String, Flux<String>> splitter = ((s) -> Flux.just(s.split(delimiter)));

		Flux.just(inputs)
		.flatMap(splitter)
		.collectList()
		.map((l) -> {LOGGER.debug("Example-10 using <then>: Number of elements in Split-First-List= {}",l.size()); return l;})
		.then(Mono.just(secondList.length))
		.subscribe((c) -> LOGGER.debug("Example-10 using <then>: Numbre of elements in Second-List = {}",c));
		Thread.sleep(1000); // prevent program exiting before the previous asynchronous step completes
	}
	
	@Test
	void runExample11() throws InterruptedException {
		LOGGER.debug("\n\nRunning Example 11. Using the <thenMany> operator ");
		String[] inputs = {"one.two", "three.four.five", "six", "seven.eight", "nine", "zero"};
		String[] secondList = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen"};
		String delimiter = "\\.";
		Function<String, Flux<String>> splitter = ((s) -> Flux.just(s.split(delimiter)));

		Flux.just(inputs)
		.flatMap(splitter)
		.collectList()
		.map((l) -> {LOGGER.debug("Example-11 using <thenMany>: Number of elements in Split-First-List= {}",l.size()); return l;})
		.thenMany(Flux.just(secondList))
		.subscribe((x) -> LOGGER.debug("Example-11 using <thenMany>: From Second-List output= {}",x));
		Thread.sleep(1000); // prevent program exiting before the previous asynchronous step completes
	}
	
	@Test
	void runExample12()  throws InterruptedException {
		LOGGER.debug("\n\nRunning Example 12. Using the <zip> operator ");
		Mono.zip(Mono.just("hello"), Mono.just("world"))
		.map(tuple2 -> {

		      return tuple2.getT1() +"-" + tuple2.getT2();
		    })
		.subscribe((x) -> LOGGER.debug("Example-12 using <zip>: output= {}",x));
		Thread.sleep(1000); // prevent program exiting before the previous asynchronous step completes
	}
}

