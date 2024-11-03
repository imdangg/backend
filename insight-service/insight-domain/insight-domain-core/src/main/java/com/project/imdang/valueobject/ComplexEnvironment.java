package com.project.imdang.valueobject;

public class ComplexEnvironment {
    // 건물
    private final Opinion<ObjectiveItem> buildingCondition;
    // 안전
    private final Opinion<ObjectiveItem> security;
    // 어린이 시설
    private final Opinion<ObjectiveItem> childrenFacility;
    // 경로 시설
    private final Opinion<ObjectiveItem> seniorFacility;

    public ComplexEnvironment(Opinion<ObjectiveItem> buildingCondition, Opinion<ObjectiveItem> security, Opinion<ObjectiveItem> childrenFacility, Opinion<ObjectiveItem> seniorFacility) {
        this.buildingCondition = buildingCondition;
        this.security = security;
        this.childrenFacility = childrenFacility;
        this.seniorFacility = seniorFacility;
    }
}
