package com.project.imdang.insight.application.dto.insight;

import com.project.imdang.common.domain.valueobject.ObjectiveItem;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record ComplexEnvironmentDTO(
        // 건물
        @Schema(description = "건물")
        @NotNull
        ObjectiveItem buildingCondition,

        // 안전
        @Schema(description = "안전")
        @NotNull
        ObjectiveItem security,

        // 어린이 시설
        @Schema(description = "어린시 시설")
        @NotNull
        ObjectiveItem childrenFacility,

        String text
) {
}
