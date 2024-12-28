package com.project.imdang.insight.service.domain.valueobject;

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
    private Opinion<ObjectiveItem> buildingCondition;
    // 안전
    private Opinion<ObjectiveItem> security;
    // 어린이 시설
    private Opinion<ObjectiveItem> childrenFacility;
    // 경로 시설
    private Opinion<ObjectiveItem> seniorFacility;
}
