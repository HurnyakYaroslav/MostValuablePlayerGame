package org.games.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.games.files_content.CommonPlayerData;
import org.games.utils.CSVUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class GameService {

    private static final String SOURCES_PATH = "src/main/resources/games_data/";
    private static final Integer WINNER_BONUS = 10;

    public String getMostValuablePlayerFromCSV() {

        var playerData = getGamesPlayerData();
        var playerScoreData = playerData.stream().map(this::calculateScoreMap).toList();
        var resultMap = mergePlayerScoreData(playerScoreData);

        log.info("Result map of players: {}", resultMap);
        var mostValuablePlayer = getMostValuablePlayer(resultMap);
        log.info("Most Valuable Player: '{}' with score: '{}'", mostValuablePlayer.getKey().getNickName(), mostValuablePlayer.getValue());
        return mostValuablePlayer.getKey().getNickName();
    }

    private Map<CommonPlayerData, Long> mergePlayerScoreData(List<Map<CommonPlayerData, Long>> multiGamesData) {
        Map<CommonPlayerData, Long> resultMap = new HashMap<>();
        multiGamesData.forEach(singleGameMap ->
                singleGameMap.forEach((key, value) -> resultMap.merge(key, value, Long::sum)));
        return resultMap;
    }

    private Map<CommonPlayerData, Long> calculateScoreMap(List<CommonPlayerData> playerData) {
        var winnerTeam = getWinnerTeamByPlayerList(playerData);
        return getScorePlayerMap(playerData, winnerTeam);
    }

    private List<List<CommonPlayerData>> getGamesPlayerData() {
        return CSVUtil.getFileListByPath(SOURCES_PATH).stream()
                .map(file -> CSVUtil.readCSVGameDataFile(SOURCES_PATH + file))
                .toList();
    }

    private Pair<CommonPlayerData, Long> getMostValuablePlayer(Map<CommonPlayerData, Long> reducedGameMap) {
        return reducedGameMap.entrySet().stream()
                .max((e1, e2) -> Math.toIntExact(e1.getValue() - e2.getValue()))
                .map(entry -> Pair.of(entry.getKey(), entry.getValue()))
                .orElseThrow();
    }

    private Map<CommonPlayerData, Long> getScorePlayerMap(List<CommonPlayerData> list, String winnerTeam) {
        return list.stream()
                .map(player -> calculatePlayerPoints(player, winnerTeam))
                .collect(Collectors.toMap(Pair::getLeft, Pair::getRight));
    }

    private Pair<CommonPlayerData, Long> calculatePlayerPoints(CommonPlayerData player, String winnerTeam) {
        var countPoints = Objects.equals(player.getTeamName(), winnerTeam)
                ? player.countPoints() + WINNER_BONUS
                : player.countPoints();
        return Pair.of(player, countPoints);
    }

    private String getWinnerTeamByPlayerList(List<CommonPlayerData> list) {
        var teamMap = list.stream().reduce(new HashMap<>(), this::mergeMapAccumulator, this::putAllAndReturn);
        return teamMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey) //todo not sort, case if ==
                .findFirst().orElse("");
    }

    private Map<String, Long> putAllAndReturn(Map<String, Long> m1, Map<String, Long> m2) {
        m1.putAll(m2);
        return m1;
    }

    private Map<String, Long> mergeMapAccumulator(Map<String, Long> hashMap, CommonPlayerData e) {
        hashMap.merge(e.getTeamName(), e.countPoints(), Long::sum);
        return hashMap;
    }
}
