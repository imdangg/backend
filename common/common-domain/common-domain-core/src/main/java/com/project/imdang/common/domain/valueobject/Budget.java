package com.project.imdang.common.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Budget {
    UNDER_100M("1억 이하"), UNDER_300M("3억 이하"), UNDER_500M("5억 이하"), UNDER_700M("7억 이하"), UNDER_900M("9억 이하"),
    UNDER_1500M("15억 이하"), UNDER_2000M("20억 이하"), UNDER_3000M("30억 이하"), UNDER_5000M("50억 이하");

    private final String value;

    @JsonCreator
    public static Budget fromValue(String value) {
        return Arrays.stream(Budget.values())
                .filter(b -> b.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown value: " + value));
    }

    @JsonValue
    public String toValue() {
        return value;
    }
}
