package com.project.imdang.common.domain.valueobject.gapInvestment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 유형
 */
@Getter
@RequiredArgsConstructor
public enum HouseType {
    NEW("신축"), OLD("구축");
    private final String value;

    @JsonCreator
    public static HouseType fromValue(String value) {
        return Arrays.stream(HouseType.values())
                .filter(ht -> ht.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown value: " + value));
    }

    @JsonValue
    public String toValue() {
        return value;
    }
}
