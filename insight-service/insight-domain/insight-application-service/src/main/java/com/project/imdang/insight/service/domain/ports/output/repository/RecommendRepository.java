package com.project.imdang.insight.service.domain.ports.output.repository;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.Recommend;

import java.util.Optional;

public interface RecommendRepository {
    Optional<Recommend> findByRecommendMemberIdAndRecommendedInsightId(MemberId recommendMemberId, InsightId recommendedInsightId);
    Recommend save(Recommend recommend);
}
