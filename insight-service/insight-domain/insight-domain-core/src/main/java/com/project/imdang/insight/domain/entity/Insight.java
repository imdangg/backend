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
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class Insight extends AggregateRoot<InsightId> {

    private final MemberId memberId;  // createdBy

    private List<InsightImage> images;
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

    private ZonedDateTime createdAt;
    // TODO - CHECK : updatedAt;

    private boolean isDeleted;

    @Builder
    public Insight(InsightId id,
                   MemberId memberId,
                   List<InsightImage> images,
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
                   ZonedDateTime createdAt,
                   boolean isDeleted) {
        setId(id);
        this.memberId = memberId;
        this.images = images;
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
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
    }

    public void initialize(List<String> imageUrls) {
        InsightId insightId = new InsightId(UUID.randomUUID());
        setId(insightId);
        urlsToInsightImages(insightId, imageUrls);
        this.recommendedCount = 0L;
        this.accusedCount = 0L;
        this.viewCount = 0L;
        this.createdAt = ZonedDateTime.now();
    }

    public Insight update(MemberId updatedBy,
                          List<InsightImage> images,
                          String title,
                          Address address,
                          ApartmentComplex apartmentComplex,
                          LocalDate visitAt,
                          Set<VisitTime> visitTimes,
                          Set<VisitMethod> visitMethods,
                          Access access,
                          String summary,
                          Infra infra,
                          ComplexEnvironment complexEnvironment) {

        if (!updatedBy.equals(this.memberId)) {
            throw new InsightDomainException("Author does not match!");
        }

        if (images != null) {
            this.images = images;
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
    public Insight unRecommend() {
        this.recommendedCount--;
        return this;
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

    public void urlsToInsightImages(InsightId insightId, List<String> imageUrls) {
        this.images = IntStream.range(0, imageUrls.size())
                .mapToObj(i -> InsightImage.createNewInsightImage(
                        insightId,
                        0, // type: 메인이미지
                        i, // 정렬 순서
                        imageUrls.get(i)
                ))
                .collect(Collectors.toList());
    }

    public void setImages(List<InsightImage> images) {
        this.images = images;
    }
}
