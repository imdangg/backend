package com.project.imdang.common.domain.valueobject.gapInvestment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 희망 갭 정도
 */
@Getter
@RequiredArgsConstructor
public enum HopeGap {
    UPPER_20("갭20% 이상"), UPPER_30("갭30% 이상"), UPPER_40("갭40% 이상"), UPPER_50("갭50% 이상"),
    UPPER_60("갭60% 이상"), NO_MATTER("상관 없어요");

    private final String value;
}
