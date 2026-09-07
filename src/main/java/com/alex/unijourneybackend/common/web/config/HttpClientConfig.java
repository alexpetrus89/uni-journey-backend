package com.alex.unijourneybackend.common.web.config;

import java.net.http.HttpClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfig {

    /**
	 * Provides an HttpClient bean for making HTTP requests.
	 * @return HttpClient
	 */
    @Bean
    @SuppressWarnings("unused")
    HttpClient httpClient() {
        return HttpClient.newHttpClient();
    }

}
