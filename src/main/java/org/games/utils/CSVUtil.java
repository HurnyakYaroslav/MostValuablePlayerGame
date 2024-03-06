package org.games.utils;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.SneakyThrows;
import org.games.GameType;
import org.games.files.content.CommonPlayerData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVUtil {

    private static final String PATH_TO_DATA = "src/main/resources/games_data";

    @SneakyThrows
    public static Map<String, Long> readCSVContent(String filename) {
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
        var map = list.stream()
                .collect(Collectors.toMap(CommonPlayerData::getNickName,
                        CommonPlayerData::countPoints));
        return map;
    }

    public static Set<String> listRootFilesUsingJavaIO() {
        return Stream.of(new File(PATH_TO_DATA).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }
}
