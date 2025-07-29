package com.project.imdang.common.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Purpose {
    LIVING("실거주"), GAP_INVESTMENT("갭투자");
    private final String value;

    @JsonCreator
    public static Purpose fromValue(String value) {
        return Arrays.stream(Purpose.values())
                .filter(p -> p.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown value: " + value));
    }

    @JsonValue
    public String toValue() {
        return value;
    }
}
