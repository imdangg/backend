package com.project.imdang.common.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 인프라
 */
@Getter
@RequiredArgsConstructor
public enum InfraNew {
    MART("대형마트"), HOSPITAL("대형병원"), DEPARTMENT_STORE("백화점"),
    CHILD_FACILITIY("아이 동반시설"), CULTURE_FACILITY("문화시설");
    private final String value;

    @JsonCreator
    public static InfraNew fromValue(String value) {
        return Arrays.stream(InfraNew.values())
                .filter(i -> i.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown value: " + value));
    }

    @JsonValue
    public String toValue() {
        return value;
    }
}
