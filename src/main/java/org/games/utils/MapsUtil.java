package org.games.utils;

import org.games.files_content.CommonPlayerData;

import java.util.Map;

public class MapsUtil {

    public static  Map<String, Long> putAllAndReturn(Map<String, Long> m1, Map<String, Long> m2) {
        m1.putAll(m2);
        return m1;
    }

    public static Map<String, Long> mergeMapAccumulator(Map<String, Long> hashMap, CommonPlayerData e) {
        hashMap.merge(e.getTeamName(), e.countPoints(), Long::sum);
        return hashMap;
    }

}
