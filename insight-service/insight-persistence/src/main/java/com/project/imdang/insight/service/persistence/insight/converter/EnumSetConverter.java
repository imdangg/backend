package com.project.imdang.insight.service.persistence.insight.converter;

import jakarta.persistence.AttributeConverter;
import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class EnumSetConverter<E extends Enum<E>> implements AttributeConverter<Set<E>, String> {

    protected static final String DELIMITER = ",";

    private final Class<E> clazz;

    protected EnumSetConverter(Class<E> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String convertToDatabaseColumn(Set<E> enumSet) {
        if (enumSet == null || enumSet.isEmpty()) {
            return Strings.EMPTY;
        }
        return enumSet.stream()
                .map(Enum::name)
                .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public Set<E> convertToEntityAttribute(String data) {
        if (data == null || data.isEmpty()) {
            return Set.of();
        }
        return Arrays.stream(data.split(DELIMITER))
                .map(value -> Enum.valueOf(clazz, value))
                .collect(Collectors.toSet());
    }
}
