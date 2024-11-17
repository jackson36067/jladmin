package com.jackson.utils;

import java.util.Collection;

public class StringUtils {
    public static String convertCollectionToString(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            return "";
        }
        return String.join(",", collection.stream()
                .map(String::valueOf)
                .toArray(String[]::new));
    }
}
