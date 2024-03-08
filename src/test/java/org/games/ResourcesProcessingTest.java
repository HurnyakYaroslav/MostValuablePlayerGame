package org.games;

import org.games.service.GameService;
import org.games.service.GameServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResourcesProcessingTest {

    private static final String SOURCES_PATH = "src/test/resources/gamesdata/";
    private final GameService gameService = new GameServiceImpl();

    @Test
    void testGetMostValuablePlayer(){
        var mostValuablePlayer = gameService.getMostValuablePlayerFromCSV(SOURCES_PATH);
        assertEquals("nick4", mostValuablePlayer);
    }


}