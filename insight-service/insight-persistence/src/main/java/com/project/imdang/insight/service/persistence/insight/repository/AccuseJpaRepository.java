package com.project.imdang.insight.service.persistence.insight.repository;

import com.project.imdang.insight.service.persistence.insight.entity.AccuseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccuseJpaRepository extends JpaRepository<AccuseEntity, Long> {
    Optional<AccuseEntity> findByAccuseMemberIdAndAccusedInsightId(UUID accuseMemberId, UUID accusedInsightId);
}
