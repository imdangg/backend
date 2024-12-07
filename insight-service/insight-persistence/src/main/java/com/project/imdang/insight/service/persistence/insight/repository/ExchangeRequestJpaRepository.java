package com.project.imdang.insight.service.persistence.insight.repository;

import com.project.imdang.insight.service.domain.valueobject.ExchangeRequestStatus;
import com.project.imdang.insight.service.persistence.insight.entity.ExchangeRequestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExchangeRequestJpaRepository extends JpaRepository<ExchangeRequestEntity, UUID> {
    Optional<ExchangeRequestEntity> findByRequestMemberIdAndRequestedInsightId(UUID requestMemberId, UUID requestedInsightId);
    Page<ExchangeRequestEntity> findAllByRequestMemberIdAndStatus(UUID requestMemberId, ExchangeRequestStatus status, Pageable pageable);
    Page<ExchangeRequestEntity> findAllByRequestedMemberIdAndStatus(UUID requestedMemberId, ExchangeRequestStatus status, Pageable pageable);
}
