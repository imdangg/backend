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
import com.project.imdang.insight.service.domain.valueobject.VisitTime;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

@Getter
public class Snapshot extends BaseEntity<SnapshotId> {

    private final InsightId insightId;
    private final MemberId memberId;  // createdBy
    private final String mainImage;
    private final String title;

    private final Address address;
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

    @Builder
    public Snapshot(SnapshotId id,
                    InsightId insightId,
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
                    FavorableNews favorableNews) {
        setId(id);
        this.insightId = insightId;
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
    }

    private Snapshot(Insight insight) {
        this.insightId = insight.getId();
        this.memberId = insight.getMemberId();
        this.mainImage = insight.getMainImage();
        this.title = insight.getTitle();
        this.address = insight.getAddress();
        this.apartmentComplex = insight.getApartmentComplex();
        this.visitAt = insight.getVisitAt();
        this.visitTimes = insight.getVisitTimes();
        this.visitMethods = insight.getVisitMethods();
        this.access = insight.getAccess();
        this.summary = insight.getSummary();
        this.infra = insight.getInfra();
        this.complexEnvironment = insight.getComplexEnvironment();
        this.complexFacility = insight.getComplexFacility();
        this.favorableNews = insight.getFavorableNews();
    }

    static Snapshot createNewSnapshot(Insight insight) {
        return new Snapshot(insight);
    }
}
