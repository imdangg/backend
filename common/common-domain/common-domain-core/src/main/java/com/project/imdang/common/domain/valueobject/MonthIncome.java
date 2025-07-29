package com.project.imdang.common.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum MonthIncome {
    NOTHING("수입 없음"), UNDER_2M("200만원 이하"), UNDER_3M("300만원 이하"), UNDER_4M("400만원 이하"), UNDER_5M("500만원 이하"), UNDER_6M("600만원 이하"),
    UNDER_7M("700만원 이하"), UNDER_8M("800만원 이하"), UPPER_10M("1000만원 이상");

    private final String value;

    @JsonCreator
    public static MonthIncome fromValue(String value) {
        return Arrays.stream(MonthIncome.values())
                .filter(mi -> mi.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown value: " + value));
    }

    @JsonValue
    public String toValue() {
        return value;
    }
}
