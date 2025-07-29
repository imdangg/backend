package com.project.imdang.common.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 환경
 */
@Getter
@RequiredArgsConstructor
public enum Environment {
    PARK("공원"), HIKING("등산로"), HANGANG("한강"),
    RIVER("하천"), DENSE_APARTMENT("아파트 밀집");
    private final String value;

    @JsonCreator
    public static Environment fromValue(String value) {
        return Arrays.stream(Environment.values())
                .filter(e -> e.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown value: " + value));
    }

    @JsonValue
    public String toValue() {
        return value;
    }
}
