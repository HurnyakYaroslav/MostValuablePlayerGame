package org.games.filescontent;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class HandballPlayerData extends CommonPlayerData {

    @CsvBindByPosition(position = 4)
    private Long goalsMade;
    @CsvBindByPosition(position = 5)
    private Long goalsReceived;

    public Long countPlayerPoints() {
        return config.getInt("games.coefficients.handball.goalsMade") * getGoalsMade()
                - config.getInt("games.coefficients.handball.goalsReceived") * getGoalsReceived();
    }

    @Override
    public Long countTeamPoints() {
        return getGoalsMade();
    }
}
