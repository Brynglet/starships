package com.example.config;

import com.example.facade.Endpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientConfig {

    @Bean
    @Endpoint(Endpoint.APISystem.SWAPI)
    public WebClient swapiEndpoint() {
        return WebClient.builder()
                .baseUrl("https://swapi.dev/api/")
                .build();
    }
}
