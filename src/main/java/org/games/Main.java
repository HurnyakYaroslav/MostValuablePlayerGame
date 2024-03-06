package org.games;

import org.games.utils.CSVUtil;

import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println(CSVUtil.listRootFilesUsingJavaIO());
        var data = CSVUtil.listRootFilesUsingJavaIO().stream()
                .map(file -> CSVUtil.readCSVContent("src/main/resources/games_data/" + file))
                .collect(Collectors.toSet());
        System.out.println(data);

    }
}