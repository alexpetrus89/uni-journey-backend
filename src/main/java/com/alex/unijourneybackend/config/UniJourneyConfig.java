package com.alex.unijourneybackend.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;



@Configuration
public class UniJourneyConfig {

    @Bean
	@SuppressWarnings("unused")
	LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}


}



