package com.project.imdang.common.domain.valueobject.living;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 아이 유무
 */
@Getter
@RequiredArgsConstructor
public enum ChildrenPlan {
    NO("자녀 계획 없음"), YES("자녀 계획 있음"), ONE("1명"), TWO("2명"), THREE("3명");
    private final String value;
}
