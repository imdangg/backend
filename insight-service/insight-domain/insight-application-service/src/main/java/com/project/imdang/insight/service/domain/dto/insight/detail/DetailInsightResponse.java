package com.project.imdang.insight.service.domain.dto.insight.detail;

import com.project.imdang.insight.service.domain.valueobject.Access;
import com.project.imdang.insight.service.domain.valueobject.Address;
import com.project.imdang.insight.service.domain.valueobject.ComplexEnvironment;
import com.project.imdang.insight.service.domain.valueobject.ComplexFacility;
import com.project.imdang.insight.service.domain.valueobject.FavorableNews;
import com.project.imdang.insight.service.domain.valueobject.Infra;
import com.project.imdang.insight.service.domain.valueobject.VisitMethod;
import lombok.AccessLevel;
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
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DetailInsightResponse {

    private UUID memberId;
//    private MemberResponse member;
    private UUID insightId;

    private Address address;
    private String title;
    private String contents;
    private Set<String> images;
    private String summary;

    private ZonedDateTime visitAt;
    private VisitMethod visitMethod;
    private Access access;

    private Infra infra;
    private ComplexEnvironment complexEnvironment;
    private ComplexFacility complexFacility;
    private FavorableNews favorableNews;

    private Integer recommendedCount;
    private Integer accusedCount;
    private Integer viewCount;

    private Integer score;
    private ZonedDateTime createdAt;
}
