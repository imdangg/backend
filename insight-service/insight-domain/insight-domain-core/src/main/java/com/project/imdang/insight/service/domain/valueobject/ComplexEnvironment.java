package com.project.imdang.insight.service.domain.valueobject;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "건물")
    private Opinion<ObjectiveItem> buildingCondition;
    // 안전
    @Schema(description = "안전")
    private Opinion<ObjectiveItem> security;
    // 어린이 시설
    @Schema(description = "어린시 시설")
    private Opinion<ObjectiveItem> childrenFacility;
    // 경로 시설
    @Schema(description = "경로 시설")
    private Opinion<ObjectiveItem> seniorFacility;
}
