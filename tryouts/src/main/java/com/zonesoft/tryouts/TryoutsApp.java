package com.zonesoft.tryouts;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TryoutsApp {
//public class TryoutsApp implements CommandLineRunner {
	
//	private static final Logger LOGGER = LoggerFactory.getLogger(TryoutsApp.class);
	
	public static void main(String[] args) {
		SpringApplication.run(TryoutsApp.class, args);
	}

//	  @Override
//	  public void run(String... args) throws Exception {
//		  int j = 0;
//		  LOGGER.debug("args.length={}", args.length);
//		  System.out.println("args.length=" + args.length);
//		  for(String arg: args) {			  
//			  LOGGER.debug("commandline-arg[{}] = {}",j, arg);
//			  System.out.println("commandline-arg[" + j + "] = " + arg);
//			  j++;
//		  }
//	  }	
	
}
