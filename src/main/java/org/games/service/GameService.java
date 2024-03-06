package org.games.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.games.utils.CSVUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class GameService {

    private static final String SOURCES_ROOT = "src/main/resources/games_data/";

    private final CSVUtil csvUtil = new CSVUtil();

    public String calculateMVP() {

        var data = getGamesMapList();

        Map<String, Long> resultMap = new HashMap<>();
        data.forEach(singleGameMap -> singleGameMap.forEach((key, value) -> resultMap.merge(key, value, Long::sum)));

        log.info("Result map of players: {}", resultMap);

        var mostValuablePlayer = getMostValuablePlayer(resultMap);

        log.info("Most Valuable Player: '{}' with score: '{}'", mostValuablePlayer.getKey(), mostValuablePlayer.getValue());
        return mostValuablePlayer.getKey();
    }

    private List<Map<String, Long>> getGamesMapList() {
        return CSVUtil.listRootFilesUsingJavaIO().stream()
                .map(file -> csvUtil.readCSVContent(SOURCES_ROOT + file))
                .toList();
    }

    private Pair<String, Long> getMostValuablePlayer(Map<String, Long> reducedGameMap) {
        return reducedGameMap.entrySet().stream()
                .max((e1, e2) -> Math.toIntExact(e1.getValue() - e2.getValue()))
                .map(entry -> Pair.of(entry.getKey(), entry.getValue()))
                .orElseThrow();
    }
}
