package com.project.imdang.common.domain.valueobject.living;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 함께 살 인원
 */
@Getter
@RequiredArgsConstructor
public enum LivingPerson {
    NEW_COUPLE("신혼부부"), FAMILY("가족(3인 이상)"), OLD_COUPLE("중년부부"), SINGLE("혼자");

    private final String value;
}
