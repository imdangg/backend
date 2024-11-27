package com.project.imdang.insight.service.domain.entity;


import com.project.imdang.domain.entity.BaseEntity;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.valueobject.Access;
import com.project.imdang.insight.service.domain.valueobject.Address;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.domain.valueobject.ComplexEnvironment;
import com.project.imdang.insight.service.domain.valueobject.ComplexFacility;
import com.project.imdang.insight.service.domain.valueobject.FavorableNews;
import com.project.imdang.insight.service.domain.valueobject.Infra;
import com.project.imdang.insight.service.domain.valueobject.SnapshotId;
import com.project.imdang.insight.service.domain.valueobject.VisitMethod;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.Set;

@Getter
public class Snapshot extends BaseEntity<SnapshotId> {

    private InsightId insightId;
    private MemberId memberId;  // createdBy

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

    private Snapshot(InsightId insightId, MemberId memberId, Address address, ApartmentComplex apartmentComplex, String title, String contents, Set<String> images, String summary, ZonedDateTime visitAt, VisitMethod visitMethod, Access access, Infra infra, ComplexEnvironment complexEnvironment, ComplexFacility complexFacility, FavorableNews favorableNews) {
        this.insightId = insightId;
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
    }

    public Snapshot(Insight insight) {
        this.insightId = insight.getId();
        this.memberId = insight.getMemberId();
        this.address = insight.getAddress();
        this.apartmentComplex = insight.getApartmentComplex();
        this.title = insight.getTitle();
        this.contents = insight.getContents();
        this.images = insight.getImages();
        this.summary = insight.getSummary();
        this.visitAt = insight.getVisitAt();
        this.visitMethod = insight.getVisitMethod();
        this.access = insight.getAccess();
        this.infra = insight.getInfra();
        this.complexEnvironment = insight.getComplexEnvironment();
        this.complexFacility = insight.getComplexFacility();
        this.favorableNews = insight.getFavorableNews();
    }
}
