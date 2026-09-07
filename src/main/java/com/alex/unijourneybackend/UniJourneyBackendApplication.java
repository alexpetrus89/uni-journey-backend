package com.alex.unijourneybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.alex.unijourneybackend.system.UniJourneySystemManager;


@EnableScheduling
@EnableCaching
@SpringBootApplication
public class UniJourneyBackendApplication {

	/**
	 * Entry point of the program.
	 * @param args The command line arguments passed to the program.
	 */
	public static void main(String[] args) {

		ConfigurableApplicationContext ctx =
            SpringApplication.run(UniJourneyBackendApplication.class, args);

		UniJourneySystemManager.setArgs(args);
		UniJourneySystemManager.setContext(ctx);
	}


}
