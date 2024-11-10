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
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.Set;

@Getter
public class Insight extends AggregateRoot<InsightId> {
/*
1번 인사이트(1번 v1) -> 2번 인사이트(1번 v2) -> 4번 인사이트(1번 v3)
*/
    // TODO - final
    private InsightId originalId;
    private int version;

    private MemberId memberId;

    private final Address address;
    private final ApartmentComplex apartmentComplex;

    private final String title;
    private final String contents;
    // TODO - 대표사진
    private final Set<String> images;
    private final String summary;

    private final ZonedDateTime visitAt;
    private final VisitMethod visitMethod;
    private final Access access;
    // 인프라
    private final Infra infra;
    // 단지 환경
    private final ComplexEnvironment complexEnvironment;
    // 단지 시설
    private final ComplexFacility complexFacility;
    // (예정된) 호재
    private final FavorableNews favorableNews;

    private Set<MemberId> holderIds;
    // TODO - CHECK : ENUM STATUS
    private Boolean isDeleted;

    // 신고 횟수
    private int accusedCount;
    private int recommendedCount;
    private ZonedDateTime createdAt;

    private Insight(MemberId memberId,
                    Address address, ApartmentComplex apartmentComplex,
                    String title, String contents, Set<String> images, String summary,
                    ZonedDateTime visitAt, VisitMethod visitMethod, Access access,
                    Infra infra, ComplexEnvironment complexEnvironment, ComplexFacility complexFacility, FavorableNews favorableNews) {
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

        this.isDeleted = Boolean.FALSE;
        this.accusedCount = 0;
        this.recommendedCount = 0;
        this.version = 1;
        this.createdAt = ZonedDateTime.now();
    }

    // TODO - 동시성 체크
    public void accuse() {
        this.accusedCount++;
        // TODO - 신고 횟수에 따른 이벤트 발생
    }

    // TODO - 동시성 체크
    public void recommend() {
        this.recommendedCount++;
    }

    // TODO - CHECK : OR in domainService
//    public static Insight createNewInsight(MemberId memberId, Address address, ApartmentComplex apartmentComplex, String title, String contents, Set<String> images) {
//        Insight insight = new Insight(memberId, address, apartmentComplex, title, contents, images);
//        insight.validateAndEvaluate();
//        return insight;
//    }

    private Insight copy() {
        return null;
    }

    private void increaseVersion() {
        this.version++;
    }

    public Insight update() {
        Insight insight = this.copy();

        // update

        insight.validateAndEvaluate();
        // TODO - CHECK
        insight.increaseVersion();
        return insight;
    }

    // 완성도 평가
    private void validateAndEvaluate() {

        // 완료율 계산

        // 1. 필수 항목 미작성
        // 2. 완료율 80% 미만/이상
    }


    public void delete() {
        this.isDeleted = Boolean.TRUE;
    }

    public void exchange(Insight counterpartInsight) {
        MemberId counterpartMemberId = counterpartInsight.memberId;
        this.holderIds.add(counterpartMemberId);

        // TODO - counterpartInsight.exchange();
        // 교환한 insight의 holderIds에 memberId 추가
        counterpartInsight.holderIds.add(memberId);
    }
}
