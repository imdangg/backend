package com.project.imdang.insight.persistence.mapper;

import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.entity.Insight;
import com.project.imdang.insight.persistence.entity.InsightEntity;
import org.springframework.stereotype.Component;

@Component
public class InsightPersistenceMapper {

    public InsightEntity insightToInsightEntity(Insight insight) {
        return InsightEntity.builder()
                .id(insight.getId().getValue())
                .memberId(insight.getMemberId().getValue())
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
                .recommendedCount(insight.getRecommendedCount())
                .accusedCount(insight.getAccusedCount())
                .viewCount(insight.getViewCount())
                .createdAt(insight.getCreatedAt())
                .isDeleted(insight.isDeleted())
                .build();
    }

    public Insight insightEntityToInsight(InsightEntity insightEntity) {
        return Insight.builder()
                .id(new InsightId(insightEntity.getId()))
                .memberId(new MemberId(insightEntity.getMemberId()))
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
                .recommendedCount(insightEntity.getRecommendedCount())
                .accusedCount(insightEntity.getAccusedCount())
                .viewCount(insightEntity.getViewCount())
                .createdAt(insightEntity.getCreatedAt())
                .build();
    }
}
