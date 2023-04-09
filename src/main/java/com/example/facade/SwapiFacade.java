package com.example.facade;

import com.example.domain.SwapiResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SwapiFacade {

    private final WebClient webClient;

    @Autowired
    public SwapiFacade(@Endpoint(Endpoint.APISystem.SWAPI) WebClient webClient) {
        this.webClient = webClient;
    }

    public SwapiResp getSwapiResponse() {
        return webClient
                .get()
                .uri("/starships")
                .retrieve()
                .bodyToMono(SwapiResp.class)
                .block();
    }
}
