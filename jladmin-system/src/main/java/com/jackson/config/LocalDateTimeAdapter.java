package com.jackson.config;

/**
 * localDateTime适配器
 */
import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
        // 这里可以根据需要选择序列化为时间戳或 ISO 格式字符串
        long millis = src.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return new JsonPrimitive(millis);
    }

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        try {
            // 尝试将 JSON 元素作为数字处理
            long timestamp = json.getAsLong();
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        } catch (NumberFormatException e) {
            // 如果不是数字，则按 ISO 格式解析
            return LocalDateTime.parse(json.getAsString(), formatter);
        }
    }
}

