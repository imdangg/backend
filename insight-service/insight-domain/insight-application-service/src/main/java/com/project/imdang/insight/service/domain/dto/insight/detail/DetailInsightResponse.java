package com.project.imdang.insight.service.domain.dto.insight.detail;

import com.project.imdang.insight.service.domain.valueobject.Access;
import com.project.imdang.insight.service.domain.valueobject.Address;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
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

    private Address address;
    private ApartmentComplex apartmentComplex;

    private String title;
    private String contents;
    private String mainImage;
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

    public DetailInsightResponse toPreviewInsightResponse() {
        return DetailInsightResponse.builder()
                .insightId(insightId)
                .snapshotId(snapshotId)
                .recommendedCount(recommendedCount)
                .address(address)
                .apartmentComplex(apartmentComplex)
                .title(title)
                .mainImage(mainImage)
                .memberId(memberId)
//                .member(member)
                .createdAt(createdAt)
                .score(score)
                .build();
    }
}
