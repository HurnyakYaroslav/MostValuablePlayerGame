package org.games.filescontent;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.games.exceptions.RequestingGameNotSupportedException;
import org.games.utils.PropertyLoader;

@Data
public abstract class CommonPlayerData {

    protected static final PropertiesConfiguration config = PropertyLoader.getPropertyConfiguration();

    @CsvBindByPosition(position = 0)
    private String playerName;
    @CsvBindByPosition(position = 1)
    private String nickName;
    @CsvBindByPosition(position = 2)
    private Long number;
    @CsvBindByPosition(position = 3)
    private String teamName;

    public Long countPoints() {
        throw new RequestingGameNotSupportedException();
    }

}
