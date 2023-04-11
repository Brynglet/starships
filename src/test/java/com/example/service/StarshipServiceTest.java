package com.example.service;

import com.example.domain.Starship;
import com.example.domain.SwapiResponse;
import com.example.exception.ApiError;
import com.example.facade.SwapiFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class StarshipServiceTest {

    private static final String NAME1 = "name1";
    private static final String NAME2= "name2";
    private static final String ONE = "1";
    private static final String TWO = "2";

    @Mock
    private SwapiFacade swapiFacade;

    @InjectMocks
    private StarshipService starshipService;

    @Test
    public void testGetShipsOk() {

        SwapiResponse swapiResponse = new SwapiResponse();

        Starship starship1 = new Starship();
        starship1.setName(NAME1);
        starship1.setCostInCredits(ONE);

        Starship starship2 = new Starship();
        starship2.setName(NAME2);
        starship2.setCostInCredits(TWO);

        List<Starship> starShipsSortedUnSorted = Arrays.asList(starship1, starship2);

        swapiResponse.setStarships(starShipsSortedUnSorted);

        when(swapiFacade.getSwapiResponse()).thenReturn(swapiResponse);

        List<Starship> actualAndSorted = starshipService.getShips();

        assertEquals(2, actualAndSorted.size());
        assertEquals(TWO, actualAndSorted.get(0).getCostInCredits());
        assertEquals(ONE, actualAndSorted.get(1).getCostInCredits());
    }

    @Test(expected = ApiError.class)
    public void testGetShipsNotFoundException() {
        when(swapiFacade.getSwapiResponse()).thenThrow(new ApiError(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase()));
        try {
            starshipService.getShips();
        } catch (Exception e) {
            assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage());
            throw e;
        }
    }
}