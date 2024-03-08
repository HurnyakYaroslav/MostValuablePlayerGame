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
public class HandballPlayerData extends CommonPlayerData {

    @CsvBindByPosition(position = 4, required = true)
    private Integer goalsMade;
    @CsvBindByPosition(position = 5, required = true)
    private Integer goalsReceived;

    public Long countPlayerPoints() {
        return config.getLong("games.coefficients.handball.goalsMade", 0) * getGoalsMade()
                - config.getLong("games.coefficients.handball.goalsReceived", 0) * getGoalsReceived();
    }

    @Override
    public Long countTeamPoints() {
        return getGoalsMade().longValue();
    }
}
