package com.project.imdang.common.domain.valueobject.living;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 교통
 */
@Getter
@RequiredArgsConstructor
public enum Traffic {
    STATION_AREA("역세권"), BUS_STOP("버스정류장 가까움"), CONVENIENT_PARKING("주차 편리"),
    CONNECT_PARKING("주차장 엘리베이터 연결"), COMMUTING_CAR("자차 출퇴근 편리");

    private final String value;

    @JsonCreator
    public static Traffic fromValue(String value) {
        return Arrays.stream(Traffic.values())
                .filter(t -> t.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown value: " + value));
    }

    @JsonValue
    public String toValue() {
        return value;
    }
}
