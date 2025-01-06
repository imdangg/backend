package com.project.imdang.insight.service.domain.dto.insight.update;

import com.project.imdang.insight.service.domain.valueobject.Access;
import com.project.imdang.insight.service.domain.valueobject.ComplexEnvironment;
import com.project.imdang.insight.service.domain.valueobject.ComplexFacility;
import com.project.imdang.insight.service.domain.valueobject.FavorableNews;
import com.project.imdang.insight.service.domain.valueobject.Infra;
import com.project.imdang.insight.service.domain.valueobject.VisitMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateInsightCommand {
    // TODO - 수정 가능한 항목
    private UUID insightId;
    // TODO - validation
    @Setter
    private UUID memberId;

    @Schema(description = "인사이트 작성 점수")
    private int score;
    @Schema(description = "제목")
    private String title;
    @Schema(description = "내용")
    private String contents;
    @Schema(description = "메인 이미지")
    private String mainImage;
    @Schema(description = "요약")
    private String summary;

    @Schema(description = "방문 날짜", example = "2024-12-31")
    private ZonedDateTime visitAt;
    @Schema(description = "교통 수단")
    private VisitMethod visitMethod;
    @Schema(description = "출입 제한")
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
