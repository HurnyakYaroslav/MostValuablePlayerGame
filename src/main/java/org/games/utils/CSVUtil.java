package org.games.utils;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.games.exceptions.RequestingGameNotSupportedException;
import org.games.exceptions.SourcesDirectoryNotFoundException;
import org.games.exceptions.SourcesFileParsingException;
import org.games.model.GameType;
import org.games.filescontent.CommonPlayerData;

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
    private static final String NO_SOURCES_EXCEPTION_MESSAGE = "Exception when trying to get list of sources files: {%s}";
    private static final String FILE_PARSING_EXCEPTION_MESSAGE = "Exception during parsing sources file: {%s}";

    public static List<CommonPlayerData> readCSVGameDataFile(String filename) {
        var file = new File(filename);
        CsvToBean<CommonPlayerData> csvReader;
        try {
            var reader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            var game = GameType.valueOf(reader.readLine()); //first line must be game type (f.e. BASKETBALL)
            csvReader = buildCSVReader(reader, game);
        } catch (IOException e) {
            throw new SourcesFileParsingException(String.format(FILE_PARSING_EXCEPTION_MESSAGE, file.getAbsoluteFile()));
        } catch (IllegalArgumentException e){
            throw new RequestingGameNotSupportedException();
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
        var files = Optional.ofNullable(new File(directoryPath).listFiles())
                .orElseThrow(() -> new SourcesDirectoryNotFoundException(
                        String.format(NO_SOURCES_EXCEPTION_MESSAGE, directoryPath)));
        return Stream.of(files)
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }
}
