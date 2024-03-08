package org.games.utils;

import org.games.filescontent.BasketballPlayerData;
import org.games.filescontent.HandballPlayerData;

public class CommonPlayerDataUtil {

    public static BasketballPlayerData buildBasketballPlayerData(){
        return BasketballPlayerData.builder()
                .rebounds(1)
                .scoredPoints(9)
                .assists(5).build();
    }

    public static HandballPlayerData buildHandballPlayerData(){
        return HandballPlayerData.builder()
                .goalsMade(13)
                .goalsReceived(9)
                .build();
    }

}
