package org.games.utils;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.games.model.GameType;
import org.games.files_content.CommonPlayerData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVUtil {

    private static final Character CSV_SEPARATOR = ';';

    public static List<CommonPlayerData> readCSVGameDataFile(String filename) {
        var file = new File(filename);
        CsvToBean<CommonPlayerData> csvReader;
        try {
            var reader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            var game = GameType.valueOf(reader.readLine());
            csvReader = buildCSVReader(reader, game);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
