package org.games;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.games.files.content.BasketballPlayerData;
import org.games.files.content.HandballPlayerData;

@RequiredArgsConstructor
@Getter
public enum GameType {

    HANDBALL(HandballPlayerData.class), BASKETBALL(BasketballPlayerData.class);

    private final Class dataClass;
}
