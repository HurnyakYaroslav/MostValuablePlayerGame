package org.games.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.tuple.Pair;
import org.games.filescontent.CommonPlayerData;
import org.games.utils.CSVUtil;
import org.games.utils.MapsUtil;
import org.games.utils.PropertyLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class GameServiceImpl implements GameService{

    private static final PropertiesConfiguration config = PropertyLoader.getPropertyConfiguration();
    private static final String SOURCES_PATH = "src/main/resources/gamesdata/";

    public String getMostValuablePlayerFromCSV() {
        var playerData = getGamesPlayerData();
        var playerScoreData = playerData.stream().map(this::calculateScoreMap).toList();
        var resultMap = mergePlayerScoreData(playerScoreData);
        log.debug("Result map of players: {}", resultMap);
        var mostValuablePlayer = getMostValuablePlayer(resultMap);
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
                ? player.countPoints() + config.getInt("common.winnerBonus")
                : player.countPoints();
        return Pair.of(player, countPoints);
    }

    private String getWinnerTeamByPlayerList(List<CommonPlayerData> list) {
        var teamMap = list.stream().reduce(new HashMap<>(), MapsUtil::mergeMapAccumulator, MapsUtil::putAllAndReturn);
        return teamMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .findFirst().orElse("");    //expected that no teams with equal score
    }

}
