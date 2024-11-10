package com.jackson.util;

import java.util.List;
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

    /**
     * 比较两个列表，将 list2 中不存在于 list1 的元素添加到 list1 中
     * @param list1 目标列表
     * @param list2 被比较的列表
     */
    public static <T> void mergeLists(List<T> list1, List<T> list2) {
        for (T item : list2) {
            if (!list1.contains(item)) {
                list1.add(item);
            }
        }
    }
}
