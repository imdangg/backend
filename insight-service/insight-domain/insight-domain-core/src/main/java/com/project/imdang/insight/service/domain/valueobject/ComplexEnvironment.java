package com.project.imdang.insight.service.domain.valueobject;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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

    public ComplexEnvironment(Opinion<ObjectiveItem> buildingCondition, Opinion<ObjectiveItem> security, Opinion<ObjectiveItem> childrenFacility, Opinion<ObjectiveItem> seniorFacility) {
        this.buildingCondition = buildingCondition;
        this.security = security;
        this.childrenFacility = childrenFacility;
        this.seniorFacility = seniorFacility;
    }
}
