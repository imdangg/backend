package com.project.imdang.common.domain.valueobject.living;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 아이 유무
 */
@Getter
@RequiredArgsConstructor
public enum ChildrenPlan {
    NO("자녀 계획 없음"), YES("자녀 계획 있음"), ONE("1명"), TWO("2명"), THREE("3명");
    private final String value;

    @JsonCreator
    public static ChildrenPlan fromValue(String value) {
        return Arrays.stream(ChildrenPlan.values())
                .filter(cp -> cp.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown value: " + value));
    }

    @JsonValue
    public String toValue() {
        return value;
    }
}
