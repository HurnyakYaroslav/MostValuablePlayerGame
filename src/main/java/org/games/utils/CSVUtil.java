package org.games.utils;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.SneakyThrows;
import org.games.model.GameType;
import org.games.files_content.CommonPlayerData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVUtil {

    private static final Character CSV_SEPARATOR = ';';

    @SneakyThrows
    public static List<CommonPlayerData> readCSVGameDataFile(String filename) {
        var file = new File(filename);
        var reader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
        var game = GameType.valueOf(reader.readLine());
        var csvReader = buildCSVReader(reader, game);
        return csvReader.parse();
    }

    private static CsvToBean<CommonPlayerData> buildCSVReader(BufferedReader reader, GameType game){
        return new CsvToBeanBuilder<CommonPlayerData>(reader)
                .withType(game.getDataClass())
                .withSeparator(CSV_SEPARATOR)
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .build();
    }

    public static Set<String> getFileListByPath(String directoryPath) {
        var files = Optional.ofNullable(new File(directoryPath).listFiles()).orElseThrow();
        return Stream.of(files)
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }
}
