package org.games;

import lombok.extern.slf4j.Slf4j;
import org.games.service.GameService;
import org.games.service.GameServiceImpl;

@Slf4j
public class Main {

    private static final String SOURCES_PATH = "src/main/resources/gamesdata/";
    private static final GameService gameService = new GameServiceImpl();

    public static void main(String[] args) {
        var mostValuablePlayer = gameService.getMostValuablePlayerFromCSV(SOURCES_PATH);
        log.info("Most Valuable player: '{}'", mostValuablePlayer);
    }
}