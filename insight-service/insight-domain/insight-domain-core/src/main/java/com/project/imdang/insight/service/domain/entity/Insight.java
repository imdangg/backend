package com.project.imdang.insight.service.domain.entity;


import com.project.imdang.domain.entity.AggregateRoot;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.exception.InsightDomainException;
import com.project.imdang.insight.service.domain.valueobject.Access;
import com.project.imdang.insight.service.domain.valueobject.Address;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.domain.valueobject.ComplexEnvironment;
import com.project.imdang.insight.service.domain.valueobject.ComplexFacility;
import com.project.imdang.insight.service.domain.valueobject.FavorableNews;
import com.project.imdang.insight.service.domain.valueobject.Infra;
import com.project.imdang.insight.service.domain.valueobject.VisitMethod;
import com.project.imdang.insight.service.domain.valueobject.VisitTime;
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
    // 단지 시설
    private ComplexFacility complexFacility;
    // (예정된) 호재
    private FavorableNews favorableNews;

//    private Boolean isDeleted;

    private int recommendedCount;
    // 신고 횟수
    private int accusedCount;
    private int viewCount;

    // 완성도
    private int score;
    private ZonedDateTime createdAt;
    // TODO - CHECK : updatedAt;

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
                   ComplexFacility complexFacility,
                   FavorableNews favorableNews,
                   int recommendedCount,
                   int accusedCount,
                   int viewCount,
                   int score,
                   ZonedDateTime createdAt) {
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
        this.complexFacility = complexFacility;
        this.favorableNews = favorableNews;
        this.recommendedCount = recommendedCount;
        this.accusedCount = accusedCount;
        this.viewCount = viewCount;
        this.score = score;
        this.createdAt = createdAt;
    }

    public Snapshot capture() {
        return Snapshot.createNewSnapshot(this);
    }

    public void initialize(String mainImage) {
        InsightId insightId = new InsightId(UUID.randomUUID());
        setId(insightId);
        this.mainImage = mainImage;
//        this.isDeleted = Boolean.FALSE;
        this.recommendedCount = 0;
        this.accusedCount = 0;
        this.viewCount = 0;
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
                          ComplexFacility complexFacility,
                          FavorableNews favorableNews,
                          int score) {
        // 교환 완료 시점의 인사이트 내용 유지
        // 교환 후 해당 인사이트가 수정되어도 수정사항 반영 X
        // insight가 수정되면 snapshot 생성 및 저장

        if (!updatedBy.equals(this.memberId)) {
            throw new InsightDomainException("Author does not match!");
        }

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
        this.complexFacility = complexFacility;
        this.favorableNews = favorableNews;
        this.score = score;
        return this;
    }
/*
    public void delete() {
        // 교환 완료 시점의 인사이트 내용 유지
        // 교환 후 해당 인사이트가 삭제되어도, 교환한 유저의 보관함에는 그대로 유지
        this.isDeleted = Boolean.TRUE;
    }*/

    // TODO - 동시성 체크
    public void recommend() {
        this.recommendedCount++;
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

    public void exchange(Insight counterpartInsight) {
        MemberId counterpartMemberId = counterpartInsight.memberId;
//        this.holderIds.add(counterpartMemberId);

        // TODO - counterpartInsight.exchange();
        // 교환한 insight의 holderIds에 memberId 추가
//        counterpartInsight.holderIds.add(memberId);
    }
}
