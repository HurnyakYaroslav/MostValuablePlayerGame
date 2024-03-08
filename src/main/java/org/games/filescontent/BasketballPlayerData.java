package org.games.filescontent;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class BasketballPlayerData extends CommonPlayerData {

    @CsvBindByPosition(position = 4, required = true)
    private Integer scoredPoints;
    @CsvBindByPosition(position = 5, required = true)
    private Integer rebounds;
    @CsvBindByPosition(position = 6, required = true)
    private Integer assists;

    public Long countPlayerPoints() {
        return config.getLong("games.coefficients.basketball.scoredPoints", 0) * getScoredPoints()
                + config.getLong("games.coefficients.basketball.rebounds", 0) * getRebounds()
                + config.getLong("games.coefficients.basketball.assists", 0) * getAssists();
    }

    @Override
    public Long countTeamPoints() {
        return countPlayerPoints();
    }

}
