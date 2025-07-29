package com.project.imdang.common.domain.valueobject.living;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 함께 살 인원
 */
@Getter
@RequiredArgsConstructor
public enum LivingPerson {
    NEW_COUPLE("신혼부부"), FAMILY("가족(3인 이상)"), OLD_COUPLE("중년부부"), SINGLE("혼자");

    private final String value;

    @JsonCreator
    public static LivingPerson fromValue(String value) {
        return Arrays.stream(LivingPerson.values())
                .filter(lp -> lp.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown value: " + value));
    }

    @JsonValue
    public String toValue() {
        return value;
    }
}
