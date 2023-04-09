package com.example.service;

import com.example.domain.Starship;
import com.example.facade.SwapiFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.domain.Common.NUMBER_OF_STARSHIPS;
import static com.example.domain.Common.NUMBER_PATTERN;
import static java.util.Comparator.comparingLong;

@Service
public class StarshipService {

    private final SwapiFacade swapiFacade;

    @Autowired
    public StarshipService(SwapiFacade swapiFacade) {
        this.swapiFacade = swapiFacade;
    }

    public List<Starship> getShips() {
        return swapiFacade.getSwapiResponse()
                .getStarships()
                .stream()
                .filter(s -> isANumber(s.getCostInCredits()))
                .sorted(Collections.reverseOrder(comparingLong(s->Long.valueOf(s.getCostInCredits()))))
                .limit(NUMBER_OF_STARSHIPS)
                .collect(Collectors.toList());
    }

    private boolean isANumber(String value) {
        return !ObjectUtils.isEmpty(value) && NUMBER_PATTERN.matcher(value).matches();
    }
}
