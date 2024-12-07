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
                .address(insight.getAddress())
                .apartmentComplex(insight.getApartmentComplex())
                .title(insight.getTitle())
                .contents(insight.getContents())
                .summary(insight.getSummary())
                .visitAt(insight.getVisitAt())
                .visitMethod(insight.getVisitMethod())
                .access(insight.getAccess())
                .infra(insight.getInfra())
                .complexEnvironment(insight.getComplexEnvironment())
                .complexFacility(insight.getComplexFacility())
                .favorableNews(insight.getFavorableNews())
                .accusedCount(insight.getAccusedCount())
                .recommendedCount(insight.getRecommendedCount())
                .viewCount(insight.getViewCount())
                .score(insight.getScore())
                .createdAt(insight.getCreatedAt())
                .build();
    }

    public Insight insightEntityToInsight(InsightEntity insightEntity) {
        return Insight.builder()
                .id(new InsightId(insightEntity.getId()))
                .memberId(new MemberId(insightEntity.getMemberId()))
                .address(insightEntity.getAddress())
                .apartmentComplex(insightEntity.getApartmentComplex())
                .title(insightEntity.getTitle())
                .contents(insightEntity.getContents())
                // TODO - CHECK
                .images(null)
                .summary(insightEntity.getSummary())
                .visitAt(insightEntity.getVisitAt())
                .visitMethod(insightEntity.getVisitMethod())
                .access(insightEntity.getAccess())
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
