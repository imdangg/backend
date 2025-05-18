package com.project.imdang.insight.application.dto.insight;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record DistrictDTO(
        @Schema(description = "시/도", example = "서울특별시")
        @NotBlank
        String siDo, // 시/도 (예: 서울특별시)

        @Schema(description = "시/군/구", example = "종로구")
        @NotBlank
        String siGunGu, // 시/군/구 (예: 종로구)

        @Schema(description = "읍/면/동", example = "효제동")
        @NotBlank
        String eupMyeonDong, // 읍/면/동 (예: 효제동)

        String code
) {
}
