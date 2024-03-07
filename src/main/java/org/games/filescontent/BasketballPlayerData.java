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

    public Long countPlayerPoints() {
        return config.getInt("games.coefficients.basketball.scoredPoints") * getScoredPoints()
                + config.getInt("games.coefficients.basketball.rebounds") * getRebounds()
                + config.getInt("games.coefficients.basketball.assists") * getAssists();
    }

    @Override
    public Long countTeamPoints() {
        return countPlayerPoints();
    }

}
