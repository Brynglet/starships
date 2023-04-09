package com.example.config;

import com.example.facade.Endpoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientConfig {

    @Bean
    @Endpoint(Endpoint.APISystem.SWAPI)
    public WebClient swapiEndpoint(@Value("${swapi.ingress.uri}") String swapiUri) {
        return WebClient.builder()
                .baseUrl(swapiUri)
                .build();
    }
}
