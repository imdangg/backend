package com.project.imdang.insight.service.domain.dto.insight.create;

import com.project.imdang.insight.service.domain.valueobject.Access;
import com.project.imdang.insight.service.domain.valueobject.Address;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.domain.valueobject.ComplexEnvironment;
import com.project.imdang.insight.service.domain.valueobject.ComplexFacility;
import com.project.imdang.insight.service.domain.valueobject.FavorableNews;
import com.project.imdang.insight.service.domain.valueobject.Infra;
import com.project.imdang.insight.service.domain.valueobject.VisitMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateInsightCommand {
    // TODO : 모든 요소 필수값
    // TODO : 최소 1글자 최대 20글자

    @Setter
    private UUID memberId;  // createdBy
    @Schema(description = "인사이트 작성 점수")
    @NotNull
    @PositiveOrZero
    private int score;

    private Address address;
    @Schema(description = "아파트 단지")
    private ApartmentComplex apartmentComplex;
    @Schema(description = "제목")
    @NotBlank
    @Size(min = 1, max = 20)
    private String title;
    @Schema(description = "내용")
    private String contents;
    @Schema(description = "메인 이미지")
    private String mainImage;
    @Schema(description = "요약")
    @NotBlank
    @Size(min = 30, max = 200)
    private String summary;

    @Schema(description = "다녀온 날짜", example = "2024-12-31")
    @NotNull
    private ZonedDateTime visitAt;
    @Schema(description = "교통 수단")
    @NotNull
    private VisitMethod visitMethod;
    @Schema(description = "출입 제한")
    @NotNull
    private Access access;

    // 인프라
    @Schema(description = "인프라")
    private Infra infra;
    // 단지 환경
    @Schema(description = "단지 환경")
    private ComplexEnvironment complexEnvironment;
    // 단지 시설
    @Schema(description = "단지 시설")
    private ComplexFacility complexFacility;
    // (예정된) 호재
    @Schema(description = "호재")
    private FavorableNews favorableNews;
}
