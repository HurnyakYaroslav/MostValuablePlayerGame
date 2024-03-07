package org.games.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameServiceImplTest {

    private final GameService gameService = new GameServiceImpl();

    @Test
    void testGetMostValuablePlayer(){
        var mostValuablePlayer = gameService.getMostValuablePlayerFromCSV();
        assertEquals("nick3", mostValuablePlayer);
    }

}