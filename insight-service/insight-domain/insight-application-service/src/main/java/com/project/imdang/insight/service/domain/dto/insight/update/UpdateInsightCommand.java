package com.project.imdang.insight.service.domain.dto.insight.update;

import com.project.imdang.insight.service.domain.valueobject.Access;
import com.project.imdang.insight.service.domain.valueobject.Address;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.domain.valueobject.ComplexEnvironment;
import com.project.imdang.insight.service.domain.valueobject.ComplexFacility;
import com.project.imdang.insight.service.domain.valueobject.FavorableNews;
import com.project.imdang.insight.service.domain.valueobject.Infra;
import com.project.imdang.insight.service.domain.valueobject.VisitMethod;
import com.project.imdang.insight.service.domain.valueobject.VisitTime;
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
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateInsightCommand {
    // TODO - 수정 가능한 항목
    @NotNull
    private UUID insightId;
    // TODO - validation
    @Setter
    private UUID memberId;

    private Address address;
    private ApartmentComplex apartmentComplex;

    @Schema(description = "인사이트 작성 점수")
    @NotNull
    @PositiveOrZero
    private int score;
    @Schema(description = "제목")
    @NotBlank
    @Size(min = 1, max = 20)
    private String title;
    @Schema(description = "메인 이미지")
    @Setter
    private MultipartFile mainImage;
    @Schema(description = "요약")
    @NotBlank
    @Size(min = 30, max = 200)
    private String summary;

    // TODO - ZoneId 필요
    // private ZonedDateTime visitAt;
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
    private Infra infra;
    // 단지 환경
    private ComplexEnvironment complexEnvironment;
    // 단지 시설
    private ComplexFacility complexFacility;
    // (예정된) 호재
    private FavorableNews favorableNews;
}
