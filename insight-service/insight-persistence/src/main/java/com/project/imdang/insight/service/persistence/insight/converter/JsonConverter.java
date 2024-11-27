package com.project.imdang.insight.service.persistence.insight.converter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

public abstract class JsonConverter<T> implements AttributeConverter<T, String> {

    // TODO - CHECK : Bean
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final Class<T> clazz;

    static {
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    protected JsonConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        try {
            return attribute == null ? null : objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert JSON object to String", e);
        }
    }

    @Override
    public T convertToEntityAttribute(String data) {
        try {
            return data == null ? null : objectMapper.readValue(data, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert String to JSON object", e);
        }
    }
}
