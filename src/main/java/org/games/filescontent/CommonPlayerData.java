package org.games.filescontent;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.games.utils.PropertyLoader;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class CommonPlayerData {

    @Builder.Default
    protected static final PropertiesConfiguration config = PropertyLoader.getPropertyConfiguration();

    @CsvBindByPosition(position = 0)
    private String playerName;
    @CsvBindByPosition(position = 1, required = true)
    private String nickName;
    @CsvBindByPosition(position = 2)
    private Integer number;
    @CsvBindByPosition(position = 3, required = true)
    private String teamName;

    public abstract Long countPlayerPoints();

    public abstract Long countTeamPoints();

}
