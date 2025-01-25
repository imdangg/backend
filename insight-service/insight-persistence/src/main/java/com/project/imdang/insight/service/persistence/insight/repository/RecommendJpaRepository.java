package com.project.imdang.insight.service.persistence.insight.repository;

import com.project.imdang.insight.service.persistence.insight.entity.RecommendEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RecommendJpaRepository extends JpaRepository<RecommendEntity, Long> {
    Optional<RecommendEntity> findByRecommendMemberIdAndRecommendedInsightId(UUID recommendMemberId, UUID recommendedInsightId);
}
