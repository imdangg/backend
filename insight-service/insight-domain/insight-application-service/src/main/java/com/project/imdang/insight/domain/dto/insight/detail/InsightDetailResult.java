package com.project.imdang.insight.domain.dto.insight.detail;

import com.project.imdang.common.domain.valueobject.Access;
import com.project.imdang.common.domain.valueobject.Address;
import com.project.imdang.common.domain.valueobject.ApartmentComplex;
import com.project.imdang.common.domain.valueobject.ComplexEnvironment;
import com.project.imdang.common.domain.valueobject.Infra;
import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.common.domain.valueobject.VisitMethod;
import com.project.imdang.common.domain.valueobject.VisitTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Set;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InsightDetailResult {

    private MemberId memberId;
    private String memberNickname;

    private InsightId insightId;

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

    private Boolean recommended;    // 로그인한 사용자가 추천했는가?
    private Boolean accused;        // 로그인한 사용자가 신고했는가?
    private Integer recommendedCount;
    private Integer accusedCount;
    private Integer viewCount;

    private Integer score;
    private ZonedDateTime createdAt;
    private Boolean createdByMe;

    public InsightDetailResult toPreviewInsightResponse() {
        return InsightDetailResult.builder()
                .insightId(insightId)
                .mainImage(mainImage)
                .title(title)
                .address(address)
                .apartmentComplex(apartmentComplex)
                .visitAt(visitAt)
                .visitTimes(visitTimes)
                .visitMethods(visitMethods)
                .access(access)
                .summary(summary)
                .recommended(recommended)
                .accused(accused)
                .recommendedCount(recommendedCount)
                .accusedCount(accusedCount)
                .viewCount(viewCount)
                .memberId(memberId)
                .memberNickname(memberNickname)
                .createdAt(createdAt)
                .score(score)
                .createdByMe(createdByMe)
                .build();
    }
}
