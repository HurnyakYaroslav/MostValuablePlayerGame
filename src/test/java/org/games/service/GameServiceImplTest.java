package org.games.service;

import org.games.exceptions.DataNotFoundException;
import org.games.exceptions.SourcesDirectoryNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;


class GameServiceImplTest {

    private static final String SOURCES_PATH = "src/test/resources/gamesdata/";
    private static final String EMPTY_SOURCES_PATH = "src/test/resources/gamesdataempty/";
    private static final String CORRUPTED_SOURCES_PATH = "src/test/resources/gamesdatacorrupted/";
    private static final String NULL_SOURCES_PATH = null;
    private final GameService gameService = new GameServiceImpl();

    @Test
    void testGetMostValuablePlayer() {
        var mvp = gameService.getMostValuablePlayerFromCSV(SOURCES_PATH);

        assertEquals("nick3", mvp);
    }

    @Test
    void testGetMostValuablePlayerWhenSourcesNull() {
        assertThrowsExactly(SourcesDirectoryNotFoundException.class,
                () -> gameService.getMostValuablePlayerFromCSV(NULL_SOURCES_PATH));
    }

    @Test
    void testGetMostValuablePlayerWithEmptySources() {
        assertThrowsExactly(DataNotFoundException.class,
                () -> gameService.getMostValuablePlayerFromCSV(EMPTY_SOURCES_PATH));
    }

    @Test
    void testGetMostValuablePlayerWithCorruptedSources() {
        var exception = assertThrowsExactly(RuntimeException.class,
                () -> gameService.getMostValuablePlayerFromCSV(CORRUPTED_SOURCES_PATH));
        assertEquals("Field 'goalsReceived' is mandatory but no value was provided.", exception.getCause().getMessage());
    }

}