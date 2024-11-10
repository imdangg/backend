package com.project.imdang.insight.service.persistence.insight.mapper;

import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.persistence.insight.entity.InsightEntity;
import org.springframework.stereotype.Component;

@Component
public class InsightPersistenceMapper {

    public InsightEntity insightToInsightEntity(Insight insight) {
        return InsightEntity.builder()
                .id(insight.getId().getValue())
                .memberId(insight.getMemberId().getValue())
                .build();
    }
}
