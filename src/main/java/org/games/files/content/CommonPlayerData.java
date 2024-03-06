package org.games.files.content;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public abstract class CommonPlayerData {

    @CsvBindByPosition(position = 0)
    private String playerName;
    @CsvBindByPosition(position = 1)
    private String nickName;
    @CsvBindByPosition(position = 2)
    private Long number;
    @CsvBindByPosition(position = 3)
    private String teamName;

    public Long countPoints() {
        return 0L;
    }

}
