package com.project.imdang.common.domain.valueobject;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 인프라
 */
@Getter
@RequiredArgsConstructor
public enum InfraNew {
    MART("대형마트"), HOSPITAL("대형병원"), DEPARTMENT_STORE("백화점"),
    CHILD_FACILITIY("아이_동반시설"), CULTURE_FACILITY("문화시설");
    private final String value;
}
