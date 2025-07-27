package com.project.imdang.common.domain.valueobject;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 환경
 */
@Getter
@RequiredArgsConstructor
public enum Environment {
    PARK("공원"), HIKING("등산로"), HANGANG("한강"),
    RIVER("하천"), DENSE_APARTMENT("아파트 밀집");
    private final String value;
}
