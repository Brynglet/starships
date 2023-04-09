package com.example.controller;

import com.example.service.StarshipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@Slf4j
@RestController
public class StarShipController {

    private final StarshipService starshipService;

    @Autowired
    public StarShipController (StarshipService starshipService) {
        this.starshipService = starshipService;
    }

    @GetMapping("/ships")
    public ResponseEntity<MappingJacksonValue> getShips() {

        log.info(ZonedDateTime.now() + ". Fetching ships");

        var ships = starshipService.getShips();

        log.info(ZonedDateTime.now() + ". Fetch complete");

        return ResponseEntity.ok(new MappingJacksonValue(ships));
    }
}
