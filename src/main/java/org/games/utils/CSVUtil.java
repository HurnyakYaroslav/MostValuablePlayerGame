package org.games.utils;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.Pair;
import org.games.model.GameType;
import org.games.files.content.CommonPlayerData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVUtil {

    private static final String PATH_TO_DATA = "src/main/resources/games_data";

    @SneakyThrows
    public Map<String, Long> readCSVContent(String filename) {
        var file = new File(filename);
        var reader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
        var game = GameType.valueOf(reader.readLine());
        var csvReader = new CsvToBeanBuilder<CommonPlayerData>(reader)
                .withType(game.getDataClass())
                .withSeparator(';')
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .build();
        List<CommonPlayerData> list = csvReader.parse();
        var winnerTeam = getWinnerTeamByPlayerList(list);

        var singleGameMap = list.stream()
                .map(player -> {
                    var countPoints = player.countPoints();
                    if (Objects.equals(player.getTeamName(), winnerTeam)) {
                        countPoints += 10;
                    }
                    return Pair.of(player.getNickName(), countPoints);
                })
                .collect(Collectors.toMap(Pair::getLeft,
                        Pair::getRight));

        return singleGameMap;
    }

    private String getWinnerTeamByPlayerList(List<CommonPlayerData> list){
        var teamMap = list.stream().reduce(new HashMap<String, Long>(), (hashMap, e) -> {
                    hashMap.merge(e.getTeamName(), e.countPoints(), Long::sum);
                    return hashMap;
                },
                (m, m2) -> {
                    m.putAll(m2);
                    return m;
                });
        return teamMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey) //todo not sort, case if ==
                .findFirst().orElse("");
    }

    public static Set<String> listRootFilesUsingJavaIO() {
        var files = Optional.ofNullable(new File(PATH_TO_DATA).listFiles()).orElseThrow();
        return Stream.of(files)
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }
}
