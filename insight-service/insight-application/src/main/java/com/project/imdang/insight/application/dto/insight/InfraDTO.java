package com.project.imdang.insight.application.dto.insight;

import com.project.imdang.common.domain.valueobject.Infra;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record InfraDTO(
        // 교통
        @Schema(description = "교통")
        @NotNull
        Set<Infra.Transportation> transportations,

        // 학군
        @Schema(description = "학군")
        @NotNull
        Set<Infra.SchoolDistrict> schoolDistricts,

        // 생활 편의시설
        @Schema(description = "생활 편의 시설")
        @NotNull
        Set<Infra.Amenity> amenities,

        // 문화 및 여가시설(단지 외부)
        @Schema(description = "문화 및 여가 시설")
        @NotNull
        Set<Infra.Facility> facilities,

        // 주변 환경
        @Schema(description = "주변 환경")
        @NotNull
        Set<Infra.Surroundings> surroundings,

        @Schema(description = "인프라 총평")
        String text
) {
}
