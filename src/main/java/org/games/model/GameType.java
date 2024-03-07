package org.games.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.games.filescontent.BasketballPlayerData;
import org.games.filescontent.CommonPlayerData;
import org.games.filescontent.HandballPlayerData;

@RequiredArgsConstructor
@Getter
public enum GameType {

    HANDBALL(HandballPlayerData.class),
    BASKETBALL(BasketballPlayerData.class);

    //add other games here if needed

    private final Class<? extends CommonPlayerData> dataClass;
}
