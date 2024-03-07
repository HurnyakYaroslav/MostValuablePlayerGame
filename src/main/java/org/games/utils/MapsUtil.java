package org.games.utils;

import org.games.filescontent.CommonPlayerData;

import java.util.Map;

public class MapsUtil {

    public static  Map<String, Long> putAllAndReturn(Map<String, Long> m1, Map<String, Long> m2) {
        m1.putAll(m2);
        return m1;
    }

    public static Map<String, Long> mergeMapAccumulator(Map<String, Long> map, CommonPlayerData player) {
        map.merge(player.getTeamName(), player.countTeamPoints(), Long::sum);
        return map;
    }

}
