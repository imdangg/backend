package com.project.imdang.insight.service.domain.valueobject;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private Opinion<ObjectiveItem> buildingCondition;
    // 안전
    @Schema(description = "안전")
    @NotNull
    private Opinion<ObjectiveItem> security;
    // 어린이 시설
    @Schema(description = "어린시 시설")
    @NotNull
    private Opinion<ObjectiveItem> childrenFacility;
    // 경로 시설
    @Schema(description = "경로 시설")
    @NotNull
    private Opinion<ObjectiveItem> seniorFacility;
}
