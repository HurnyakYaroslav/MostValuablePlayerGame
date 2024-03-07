package org.games.filescontent;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BasketballPlayerData extends CommonPlayerData {

    @CsvBindByPosition(position = 4)
    private Long scoredPoints;
    @CsvBindByPosition(position = 5)
    private Long rebounds;
    @CsvBindByPosition(position = 6)
    private Long assists;

    public Long countPoints() {
        return 2 * getScoredPoints() + 5 * getRebounds() + 0 * getAssists();
    }

}
