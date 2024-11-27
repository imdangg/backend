package com.project.imdang.insight.service.domain.dto.insight.update;

import com.project.imdang.insight.service.domain.valueobject.Access;
import com.project.imdang.insight.service.domain.valueobject.ComplexEnvironment;
import com.project.imdang.insight.service.domain.valueobject.ComplexFacility;
import com.project.imdang.insight.service.domain.valueobject.FavorableNews;
import com.project.imdang.insight.service.domain.valueobject.Infra;
import com.project.imdang.insight.service.domain.valueobject.VisitMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class UpdateInsightCommand {
    // TODO - 수정 가능한 항목
    private UUID insightId;

    private int score;
    private String title;
    private String contents;
    private Set<String> images;
    private String summary;

    private ZonedDateTime visitAt;
    private VisitMethod visitMethod;
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
