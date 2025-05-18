package com.project.imdang.insight.domain.ports.output.repository;

import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.entity.Recommend;

import java.util.Optional;

public interface RecommendRepository {
    Optional<Recommend> findByRecommendMemberIdAndRecommendedInsightId(MemberId recommendMemberId, InsightId recommendedInsightId);
    Recommend save(Recommend recommend);
}
