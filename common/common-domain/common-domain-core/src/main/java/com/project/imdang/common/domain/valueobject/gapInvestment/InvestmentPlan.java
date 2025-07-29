package com.project.imdang.common.domain.valueobject.gapInvestment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 투자 계획
 */
@Getter
@RequiredArgsConstructor
public enum InvestmentPlan {
    UNDER_ONE("1년 미만"), UNDER_TWO("2년 미만"), UNDER_THREE("3년 미만"), UNDER_FIVE("5년 미만"), UNDER_DECADE("10년 미만"), UPPER_TWO_DECADE("20년 이상"),
    DONT_KNOW("아직 모르겠어요");

    private final String value;

    @JsonCreator
    public static InvestmentPlan fromValue(String value) {
        return Arrays.stream(InvestmentPlan.values())
                .filter(ip -> ip.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown value: " + value));
    }

    @JsonValue
    public String toValue() {
        return value;
    }
}
