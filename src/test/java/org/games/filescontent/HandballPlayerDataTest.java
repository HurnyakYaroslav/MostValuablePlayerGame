package org.games.filescontent;

import org.junit.jupiter.api.Test;

import static org.games.utils.CommonPlayerDataUtil.buildHandballPlayerData;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HandballPlayerDataTest {

    @Test
    void testCountPlayerPoints() {
        var playerPoints = buildHandballPlayerData().countPlayerPoints();

        assertEquals(17L, playerPoints);
    }

    @Test
    void testCountTeamPoints() {
        var playerPoints = buildHandballPlayerData().countTeamPoints();

        assertEquals(13L, playerPoints);
    }

}