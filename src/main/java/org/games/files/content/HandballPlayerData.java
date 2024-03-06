package org.games.files.content;

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

    public Long countPoints() {
        return 2 * getGoalsMade() - 1 * getGoalsReceived();
    }
}
