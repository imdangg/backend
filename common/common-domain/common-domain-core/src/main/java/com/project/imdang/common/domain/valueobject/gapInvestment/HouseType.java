package com.project.imdang.common.domain.valueobject.gapInvestment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 유형
 */
@Getter
@RequiredArgsConstructor
public enum HouseType {
    NEW("신축"), OLD("구축");
    private final String value;
}
