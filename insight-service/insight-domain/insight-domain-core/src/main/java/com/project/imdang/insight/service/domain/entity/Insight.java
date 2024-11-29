package com.project.imdang.insight.service.domain.entity;


import com.project.imdang.domain.entity.AggregateRoot;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.valueobject.Access;
import com.project.imdang.insight.service.domain.valueobject.Address;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.domain.valueobject.ComplexEnvironment;
import com.project.imdang.insight.service.domain.valueobject.ComplexFacility;
import com.project.imdang.insight.service.domain.valueobject.FavorableNews;
import com.project.imdang.insight.service.domain.valueobject.Infra;
import com.project.imdang.insight.service.domain.valueobject.VisitMethod;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
public class Insight extends AggregateRoot<InsightId> {

    private final MemberId memberId;  // createdBy

    private final Address address;
    private final ApartmentComplex apartmentComplex;

    private String title;
    private String contents;
    // TODO - 대표사진
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

//    private Boolean isDeleted;

    private int recommendedCount;
    // 신고 횟수
    private int accusedCount;
    private int viewCount;

    // 완성도
    private int score;
    private ZonedDateTime createdAt;
    // TODO - CHECK : updatedAt;

    private Insight(MemberId memberId, Address address, ApartmentComplex apartmentComplex, String title, String contents, Set<String> images, String summary, ZonedDateTime visitAt, VisitMethod visitMethod, Access access, Infra infra, ComplexEnvironment complexEnvironment, ComplexFacility complexFacility, FavorableNews favorableNews,
//                    Boolean isDeleted,
                    int recommendedCount, int accusedCount, int viewCount, int score, ZonedDateTime createdAt) {
        this.memberId = memberId;
        this.address = address;
        this.apartmentComplex = apartmentComplex;
        this.title = title;
        this.contents = contents;
        this.images = images;
        this.summary = summary;
        this.visitAt = visitAt;
        this.visitMethod = visitMethod;
        this.access = access;
        this.infra = infra;
        this.complexEnvironment = complexEnvironment;
        this.complexFacility = complexFacility;
        this.favorableNews = favorableNews;
//        this.isDeleted = isDeleted;
        this.recommendedCount = recommendedCount;
        this.accusedCount = accusedCount;
        this.viewCount = viewCount;
        this.score = score;
        this.createdAt = createdAt;
    }

    @Builder
    public Insight(MemberId memberId, int score,
                   Address address, ApartmentComplex apartmentComplex,
                   String title, String contents, Set<String> images, String summary,
                   ZonedDateTime visitAt, VisitMethod visitMethod, Access access,
                   Infra infra, ComplexEnvironment complexEnvironment, ComplexFacility complexFacility, FavorableNews favorableNews) {

        this.memberId = memberId;
        this.score = score;

        this.address = address;
        this.apartmentComplex = apartmentComplex;
        this.title = title;
        this.contents = contents;
        this.images = images;
        this.summary = summary;

        this.visitAt = visitAt;
        this.visitMethod = visitMethod;
        this.access = access;

        this.infra = infra;
        this.complexEnvironment = complexEnvironment;
        this.complexFacility = complexFacility;
        this.favorableNews = favorableNews;
    }

    // TODO - CHECK : Reflection?
    // 완성도 평가
    public void validateAndEvaluate() {
        // 1. 필수 항목 미작성
        // 2. 완료율 80% 미만/이상
        this.score = 80;
    }

    public Snapshot capture() {
        return new Snapshot(this);
    }

    public void initialize() {
        InsightId insightId = new InsightId(UUID.randomUUID());
        setId(insightId);
//        this.isDeleted = Boolean.FALSE;
        this.recommendedCount = 0;
        this.accusedCount = 0;
        this.viewCount = 0;
        this.createdAt = ZonedDateTime.now();
    }

    public Insight update(int score,
//                          Address address, ApartmentComplex apartmentComplex,
                          String title, String contents, Set<String> images, String summary,
                          ZonedDateTime visitAt, VisitMethod visitMethod, Access access,
                          Infra infra, ComplexEnvironment complexEnvironment, ComplexFacility complexFacility, FavorableNews favorableNews) {
        // 교환 완료 시점의 인사이트 내용 유지
        // 교환 후 해당 인사이트가 수정되어도 수정사항 반영 X
        // insight가 수정되면 snapshot 생성 및 저장

        this.score = score;
        this.title = title;
        this.contents = contents;
        this.images = images;
        this.summary = summary;
        this.visitAt = visitAt;
        this.visitMethod = visitMethod;
        this.access = access;
        this.infra = infra;
        this.complexEnvironment = complexEnvironment;
        this.complexFacility = complexFacility;
        this.favorableNews = favorableNews;
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
    public void accuse() {
        this.accusedCount++;
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
