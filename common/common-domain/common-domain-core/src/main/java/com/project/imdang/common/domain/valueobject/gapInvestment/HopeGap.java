package com.project.imdang.common.domain.valueobject.gapInvestment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 희망 갭 정도
 */
@Getter
@RequiredArgsConstructor
public enum HopeGap {
    UPPER_20("갭20% 이상"), UPPER_30("갭30% 이상"), UPPER_40("갭40% 이상"), UPPER_50("갭50% 이상"),
    UPPER_60("갭60% 이상"), NO_MATTER("상관 없어요");

    private final String value;

    @JsonCreator
    public static HopeGap fromValue(String value) {
        return Arrays.stream(HopeGap.values())
                .filter(hg -> hg.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown value: " + value));
    }

    @JsonValue
    public String toValue() {
        return value;
    }
}
