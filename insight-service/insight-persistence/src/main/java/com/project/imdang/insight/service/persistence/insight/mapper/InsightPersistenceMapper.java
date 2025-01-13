package com.project.imdang.insight.service.persistence.insight.mapper;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.persistence.insight.entity.InsightEntity;
import org.springframework.stereotype.Component;

@Component
public class InsightPersistenceMapper {

    public InsightEntity insightToInsightEntity(Insight insight) {
        return InsightEntity.builder()
                .id(insight.getId().getValue())
                .memberId(insight.getMemberId().getValue())
                .mainImage(insight.getMainImage())
                .title(insight.getTitle())
                .address(insight.getAddress())
                .apartmentComplex(insight.getApartmentComplex())
                .visitAt(insight.getVisitAt())
                .visitTimes(insight.getVisitTimes())
                .visitMethods(insight.getVisitMethods())
                .access(insight.getAccess())
                .summary(insight.getSummary())
                .infra(insight.getInfra())
                .complexEnvironment(insight.getComplexEnvironment())
                .complexFacility(insight.getComplexFacility())
                .favorableNews(insight.getFavorableNews())
                .recommendedCount(insight.getRecommendedCount())
                .accusedCount(insight.getAccusedCount())
                .viewCount(insight.getViewCount())
                .score(insight.getScore())
                .createdAt(insight.getCreatedAt())
                .build();
    }

    public Insight insightEntityToInsight(InsightEntity insightEntity) {
        return Insight.builder()
                .id(new InsightId(insightEntity.getId()))
                .memberId(new MemberId(insightEntity.getMemberId()))
                .mainImage(insightEntity.getMainImage())
                .title(insightEntity.getTitle())
                .address(insightEntity.getAddress())
                .apartmentComplex(insightEntity.getApartmentComplex())
                .visitAt(insightEntity.getVisitAt())
                .visitTimes(insightEntity.getVisitTimes())
                .visitMethods(insightEntity.getVisitMethods())
                .access(insightEntity.getAccess())
                .summary(insightEntity.getSummary())
                .infra(insightEntity.getInfra())
                .complexEnvironment(insightEntity.getComplexEnvironment())
                .complexFacility(insightEntity.getComplexFacility())
                .favorableNews(insightEntity.getFavorableNews())
                .recommendedCount(insightEntity.getRecommendedCount())
                .accusedCount(insightEntity.getAccusedCount())
                .viewCount(insightEntity.getViewCount())
                .score(insightEntity.getScore())
                .createdAt(insightEntity.getCreatedAt())
                .build();
    }
}
