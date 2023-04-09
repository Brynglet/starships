package com.example.facade;

import com.example.domain.ApiError;
import com.example.domain.SwapiResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@Slf4j
public class SwapiFacade {

    private final WebClient webClient;

    @Autowired
    public SwapiFacade(@Endpoint(Endpoint.APISystem.SWAPI) WebClient webClient) {
        this.webClient = webClient;
    }

    public SwapiResp getSwapiResponse() {

        try {
            return webClient
                    .get()
                    .uri("/starships")
                    .retrieve()
                    .bodyToMono(SwapiResp.class)
                    .block();
        } catch (WebClientResponseException e) {
            log.error("WebClientResponseException:" + e.toString());
            throw new ApiError(e.getStatusCode(), e.getMessage());
        }
    }
}
