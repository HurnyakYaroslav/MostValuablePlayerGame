package org.games;

import lombok.extern.slf4j.Slf4j;
import org.games.service.GameService;

@Slf4j
public class Main {

    private static final GameService gameService = new GameService();

    public static void main(String[] args) {
        var mostValuablePlayer = gameService.getMostValuablePlayerFromCSV();
        log.info("Most Valuable player: '{}'", mostValuablePlayer);
    }
}