package com.example.controller;

import com.example.domain.Starship;
import com.example.exception.ApiError;
import com.example.service.StarshipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(description = "Gets a list of starships")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Starship.class))))
    @ApiResponse(description = "Not Found", responseCode = "404",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    @ApiResponse(description = "Server Error", responseCode = "500",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    @GetMapping("/ships")
    public ResponseEntity<MappingJacksonValue> getShips() {

        log.info(ZonedDateTime.now() + ". Fetching ships");

        var ships = starshipService.getShips();

        log.info(ZonedDateTime.now() + ". Fetch complete");

        return ResponseEntity.ok(new MappingJacksonValue(ships));
    }
}
