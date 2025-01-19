package com.project.imdang.insight.service.persistence.insight.mapper;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.Recommend;
import com.project.imdang.insight.service.domain.valueobject.RecommendId;
import com.project.imdang.insight.service.persistence.insight.entity.RecommendEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RecommendPersistenceMapper {

    public RecommendEntity recommendToRecommendEntity(Recommend recommend) {
        return RecommendEntity.builder()
                .id(!Objects.isNull(recommend.getId()) ? recommend.getId().getValue() : null)
                .recommendMemberId(recommend.getRecommendMemberId().getValue())
                .recommendedInsightId(recommend.getRecommendedInsightId().getValue())
                .recommendedMemberId(recommend.getRecommendedMemberId().getValue())
                .createdAt(recommend.getCreatedAt())
                .build();
    }

    public Recommend recommendEntityToRecommend(RecommendEntity recommendEntity) {
        return Recommend.builder()
                .id(new RecommendId(recommendEntity.getId()))
                .recommendMemberId(new MemberId(recommendEntity.getRecommendMemberId()))
                .recommendedInsightId(new InsightId(recommendEntity.getRecommendedInsightId()))
                .recommendedMemberId(new MemberId(recommendEntity.getRecommendedMemberId()))
                .createdAt(recommendEntity.getCreatedAt())
                .build();
    }
}
