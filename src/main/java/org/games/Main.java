package org.games;

import org.games.utils.CSVUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        var data = CSVUtil.listRootFilesUsingJavaIO().stream()
                .map(file -> CSVUtil.readCSVContent("src/main/resources/games_data/" + file))
                .toList();

        Map<String, Long> resultMap = new HashMap<>();

        data.forEach(singleGameMap -> singleGameMap.forEach((key, value) -> resultMap.merge(key, value, Long::sum)));
        System.out.println(resultMap);
    }
}