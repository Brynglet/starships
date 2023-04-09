package com.example.service;

import com.example.pojo.Starship;
import com.example.pojo.SwapiResp;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingLong;

@Service
public class StarshipService {

/*

pattern number? 12 12.3 -1.1
    TODO:
    logging
    test
    errorhandling
    swagger
*/
    private static final int NUMBER_OF_STARSHIPS = 10;

    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    private final WebClient webClient;

    public StarshipService(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://swapi.dev/api/").build();
    }

    public List<Starship> getShips() {

        var swapiResponse = webClient
                .get()
                .uri("/starships")
                .retrieve()
                .bodyToMono(SwapiResp.class)
                .block();

        return swapiResponse.getStarships()
                .stream()
                .filter(s -> isANumber(s.getCostInCredits()))
                .sorted(Collections.reverseOrder(comparingLong(s->Long.valueOf(s.getCostInCredits()))))
                .limit(NUMBER_OF_STARSHIPS)
                .collect(Collectors.toList());
    }

    private boolean isANumber(String value) {
        return !ObjectUtils.isEmpty(value) && pattern.matcher(value).matches();
    }
}
