package com.project.imdang.common.domain.valueobject.gapInvestment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.project.imdang.common.domain.valueobject.living.Traffic;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 아파트 평수
 */
@Getter
@RequiredArgsConstructor
public enum ApartmentSquare {
    ULTRA_SAMLL("초소형(21~40m2)"), SMALL("소형(60m2 이하)"), SMALL_MEDIUM("중소형(60~82m2)"), MEDIUM_LARGE("중대형(85~102m2)"), LARGE("대형(135m2 이상)");

    private final String value;

    @JsonCreator
    public static ApartmentSquare fromValue(String value) {
        return Arrays.stream(ApartmentSquare.values())
                .filter(as -> as.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown value: " + value));
    }

    @JsonValue
    public String toValue() {
        return value;
    }
}
