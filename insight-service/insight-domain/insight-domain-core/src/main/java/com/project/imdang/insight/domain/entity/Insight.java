package com.project.imdang.insight.domain.entity;


import com.project.imdang.common.domain.entity.AggregateRoot;
import com.project.imdang.common.domain.valueobject.Access;
import com.project.imdang.common.domain.valueobject.Address;
import com.project.imdang.common.domain.valueobject.ApartmentComplex;
import com.project.imdang.common.domain.valueobject.ComplexEnvironment;
import com.project.imdang.common.domain.valueobject.Infra;
import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.common.domain.valueobject.VisitMethod;
import com.project.imdang.common.domain.valueobject.VisitTime;
import com.project.imdang.insight.domain.exception.InsightDomainException;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
public class Insight extends AggregateRoot<InsightId> {

    private final MemberId memberId;  // createdBy

    private String mainImage;
    private String title;

    private Address address;
    private ApartmentComplex apartmentComplex;

    private LocalDate visitAt;
    private Set<VisitTime> visitTimes;
    private Set<VisitMethod> visitMethods;
    private Access access;

    private String summary;

    // 인프라
    private Infra infra;
    // 단지 환경
    private ComplexEnvironment complexEnvironment;

    private Long recommendedCount;
    // 신고 횟수
    private Long accusedCount;
    private Long viewCount;

    // 완성도
    private Integer score;
    private ZonedDateTime createdAt;
    // TODO - CHECK : updatedAt;

    private boolean isDeleted;

    @Builder
    public Insight(InsightId id,
                   MemberId memberId,
                   String mainImage,
                   String title,
                   Address address,
                   ApartmentComplex apartmentComplex,
                   LocalDate visitAt,
                   Set<VisitTime> visitTimes,
                   Set<VisitMethod> visitMethods,
                   Access access,
                   String summary,
                   Infra infra,
                   ComplexEnvironment complexEnvironment,
                   Long recommendedCount,
                   Long accusedCount,
                   Long viewCount,
                   Integer score,
                   ZonedDateTime createdAt,
                   boolean isDeleted) {
        setId(id);
        this.memberId = memberId;
        this.mainImage = mainImage;
        this.title = title;
        this.address = address;
        this.apartmentComplex = apartmentComplex;
        this.visitAt = visitAt;
        this.visitTimes = visitTimes;
        this.visitMethods = visitMethods;
        this.access = access;
        this.summary = summary;
        this.infra = infra;
        this.complexEnvironment = complexEnvironment;
        this.recommendedCount = recommendedCount;
        this.accusedCount = accusedCount;
        this.viewCount = viewCount;
        this.score = score;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
    }

    public void initialize(String mainImage) {
        InsightId insightId = new InsightId(UUID.randomUUID());
        setId(insightId);
        this.mainImage = mainImage;
        this.recommendedCount = 0L;
        this.accusedCount = 0L;
        this.viewCount = 0L;
        this.createdAt = ZonedDateTime.now();
    }

    public Insight update(MemberId updatedBy,
                          String mainImage,
                          String title,
                          Address address,
                          ApartmentComplex apartmentComplex,
                          LocalDate visitAt,
                          Set<VisitTime> visitTimes,
                          Set<VisitMethod> visitMethods,
                          Access access,
                          String summary,
                          Infra infra,
                          ComplexEnvironment complexEnvironment,
                          int score) {

        if (!updatedBy.equals(this.memberId)) {
            throw new InsightDomainException("Author does not match!");
        }

        if (mainImage != null) {
            this.mainImage = mainImage;
        }
        this.title = title;
        this.address = address;
        this.apartmentComplex = apartmentComplex;
        this.visitAt = visitAt;
        this.visitTimes = visitTimes;
        this.visitMethods = visitMethods;
        this.access = access;
        this.summary = summary;
        this.infra = infra;
        this.complexEnvironment = complexEnvironment;
        this.score = score;
        return this;
    }

    public void delete() {
        this.isDeleted = true;
    }

    // TODO - 동시성 체크
    public Recommend recommend(MemberId recommendedBy) {
        this.recommendedCount++;
        return Recommend.createNewRecommend(recommendedBy, getId(), this.memberId);
    }

    // TODO - 동시성 체크
    public Accuse accuse(MemberId accusedBy) {
        this.accusedCount++;
        return Accuse.createNewAccuse(accusedBy, getId(), this.memberId);
    }
    
    // TODO - 동시성 체크
    public void view() {
        this.viewCount++;
    }
}
