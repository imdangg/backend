package com.project.imdang.insight.application.dto.insight;

import com.project.imdang.common.domain.valueobject.Access;
import com.project.imdang.common.domain.valueobject.VisitMethod;
import com.project.imdang.common.domain.valueobject.VisitTime;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateInsightRequest {
        @NotNull
        private UUID insightId;

        private AddressDTO address;
        private ApartmentComplexDTO apartmentComplex;

        @Schema(description = "인사이트 작성 점수")
        @NotNull
        @PositiveOrZero
        private int score;

        @Schema(description = "제목")
        @NotBlank
        @Size(min = 1, max = 20)
        private String title;

//        @Schema(description = "메인 이미지")
//        private MultipartFile mainImage;

        @Schema(description = "요약")
        @NotBlank
        @Size(min = 30, max = 200)
        private String summary;

        @Schema(description = "방문 날짜", example = "2024-12-31")
        @NotNull
        private LocalDate visitAt;

        @Schema(description = "방문 시간")
        @NotNull
        private Set<VisitTime> visitTimes;

        @Schema(description = "교통 수단")
        @NotNull
        private Set<VisitMethod> visitMethods;

        @Schema(description = "출입 제한")
        @NotNull
        private Access access;

        // 인프라
        private InfraDTO infra;
        // 단지 환경
        private ComplexEnvironmentDTO complexEnvironment;
}
