package com.project.imdang.common.domain.valueobject.living;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 교통
 */
@Getter
@RequiredArgsConstructor
public enum Traffic {
    STATION_AREA("역세권"), BUS_STOP("버스정류장_가까움"), CONVENIENT_PARKING("주차_편리"),
    CONNECT_PARKING("주차장_엘리베이터_연결"), COMMUTING_CAR("자차_출퇴근_편리");

    private final String value;
}
