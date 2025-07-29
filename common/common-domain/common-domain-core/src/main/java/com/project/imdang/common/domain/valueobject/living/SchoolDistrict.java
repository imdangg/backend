package com.project.imdang.common.domain.valueobject.living;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 학군
 */
@Getter
@RequiredArgsConstructor
public enum SchoolDistrict {
    ELEMENTARY_APARTMENT("초품아"), PRESTIGIOUS_SCHOOL("명문 초/중/고"), CLOSE_ACADEMY("학원가 근접"),
    BABY_COMMUNITY("육아 커뮤니티 활발"), SAFE_ROUTE("안전한 통학로");
    private final String value;

    @JsonCreator
    public static SchoolDistrict fromValue(String value) {
        return Arrays.stream(SchoolDistrict.values())
                .filter(sd -> sd.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown value: " + value));
    }

    @JsonValue
    public String toValue() {
        return value;
    }
}
