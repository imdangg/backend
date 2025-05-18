package com.project.imdang.common.domain.valueobject;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ComplexEnvironment {
    // 건물
    private ObjectiveItem buildingCondition;
    // 안전
    private ObjectiveItem security;
    // 어린이 시설
    private ObjectiveItem childrenFacility;
    private String text;
}
