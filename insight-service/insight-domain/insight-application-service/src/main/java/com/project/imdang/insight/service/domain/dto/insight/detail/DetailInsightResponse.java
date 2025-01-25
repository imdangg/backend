package com.project.imdang.insight.service.domain.dto.insight.detail;

import com.project.imdang.insight.service.domain.valueobject.Access;
import com.project.imdang.insight.service.domain.valueobject.Address;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.domain.valueobject.ComplexEnvironment;
import com.project.imdang.insight.service.domain.valueobject.ComplexFacility;
import com.project.imdang.insight.service.domain.valueobject.ExchangeRequestStatus;
import com.project.imdang.insight.service.domain.valueobject.FavorableNews;
import com.project.imdang.insight.service.domain.valueobject.Infra;
import com.project.imdang.insight.service.domain.valueobject.VisitMethod;
import com.project.imdang.insight.service.domain.valueobject.VisitTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    private Long snapshotId;

    private String mainImage;
    private String title;

    private Address address;
    private ApartmentComplex apartmentComplex;

    private LocalDate visitAt;
    private Set<VisitTime> visitTimes;
    private Set<VisitMethod> visitMethods;
    private Access access;
    private String summary;

    private Infra infra;
    private ComplexEnvironment complexEnvironment;
    private ComplexFacility complexFacility;
    private FavorableNews favorableNews;

    private Integer recommendedCount;
    private Integer accusedCount;
    private Integer viewCount;

    private Integer score;
    private ZonedDateTime createdAt;
    private ExchangeRequestStatus exchangeRequestStatus;
    private UUID exchangeRequestId;

    public DetailInsightResponse toPreviewInsightResponse() {
        return DetailInsightResponse.builder()
                .insightId(insightId)
                .snapshotId(snapshotId)
                .mainImage(mainImage)
                .title(title)
                .address(address)
                .apartmentComplex(apartmentComplex)
                .visitAt(visitAt)
                .visitTimes(visitTimes)
                .visitMethods(visitMethods)
                .access(access)
                .summary(summary)
                .recommendedCount(recommendedCount)
                .memberId(memberId)
//                .member(member)
                .createdAt(createdAt)
                .score(score)
                // TODO - CHECK
                .exchangeRequestStatus(exchangeRequestStatus)
                .build();
    }
}
