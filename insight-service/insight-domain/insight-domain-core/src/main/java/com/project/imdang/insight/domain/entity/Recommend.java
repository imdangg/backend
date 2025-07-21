package com.project.imdang.insight.domain.entity;

import com.project.imdang.common.domain.entity.BaseEntity;
import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.valueobject.RecommendId;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class Recommend extends BaseEntity<RecommendId> {

    private final MemberId recommendMemberId;      // recommendedBy

    private final InsightId recommendedInsightId;
    private final MemberId recommendedMemberId; //추천 받은 MemberID (인사이트 작성자)
    private final ZonedDateTime createdAt;

    @Builder
    public Recommend(RecommendId id, MemberId recommendMemberId, InsightId recommendedInsightId, MemberId recommendedMemberId, ZonedDateTime createdAt) {
        setId(id);
        this.recommendMemberId = recommendMemberId;
        this.recommendedInsightId = recommendedInsightId;
        this.recommendedMemberId = recommendedMemberId;
        this.createdAt = createdAt;
    }

    static Recommend createNewRecommend(MemberId recommendMemberId, InsightId recommendedInsightId, MemberId recommendedMemberId) {
        return Recommend.builder()
                .recommendMemberId(recommendMemberId)
                .recommendedInsightId(recommendedInsightId)
                .recommendedMemberId(recommendedMemberId)
                .createdAt(ZonedDateTime.now())
                .build();
    }
}
