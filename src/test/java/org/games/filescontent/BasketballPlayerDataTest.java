package org.games.filescontent;

import org.junit.jupiter.api.Test;

import static org.games.utils.CommonPlayerDataUtil.buildBasketballPlayerData;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketballPlayerDataTest {

    @Test
    void testCountPlayerPoints() {
        var playerPoints = buildBasketballPlayerData().countPlayerPoints();

        assertEquals(23L, playerPoints);
    }

    @Test
    void testCountTeamPoints() {
        var playerPoints = buildBasketballPlayerData().countTeamPoints();

        assertEquals(23L, playerPoints);
    }

}