package org.games.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.games.files_content.BasketballPlayerData;
import org.games.files_content.CommonPlayerData;
import org.games.files_content.HandballPlayerData;

@RequiredArgsConstructor
@Getter
public enum GameType {

    HANDBALL(HandballPlayerData.class),
    BASKETBALL(BasketballPlayerData.class);

    private final Class<? extends CommonPlayerData> dataClass;
}
