package com.project.imdang.common.domain.valueobject.gapInvestment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 세대수
 */
@Getter
@RequiredArgsConstructor
public enum Household {
    UPPER_100("100세대 이상"), UPPER_300("300세대 이상"), UPPER_500("500세대 이상"), UPPER_1000("1000세대 이상"),
    UPPER_2000("2000세대 이상"), UPPER_3000("3000세대 이상");

    private final String value;

    @JsonCreator
    public static Household fromValue(String value) {
        return Arrays.stream(Household.values())
                .filter(h -> h.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown value: " + value));
    }

    @JsonValue
    public String toValue() {
        return value;
    }
}
