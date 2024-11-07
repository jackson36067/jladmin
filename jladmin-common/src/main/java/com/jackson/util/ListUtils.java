package com.jackson.util;

import java.util.Set;

public class ListUtils {
    /**
     * 比较两个set集合内容是否完全一致
     *
     * @param set1
     * @param set2
     * @param <T>
     * @return
     */
    public static <T> boolean areSetsEqual(Set<T> set1, Set<T> set2) {
        if (set1 == null || set2 == null) {
            return false; // 处理 null 的情况
        }
        return set1.size() == set2.size() && set1.containsAll(set2);
    }
}
