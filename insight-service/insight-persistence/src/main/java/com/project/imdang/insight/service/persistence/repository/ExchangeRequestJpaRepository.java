package com.project.imdang.insight.service.persistence.repository;

import com.project.imdang.insight.service.domain.valueobject.ExchangeRequestStatus;
import com.project.imdang.insight.service.persistence.entity.ExchangeRequestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExchangeRequestJpaRepository extends JpaRepository<ExchangeRequestEntity, UUID> {
    List<ExchangeRequestEntity> findByRequestMemberIdAndRequestedInsightId(UUID requestMemberId, UUID requestedInsightId);
    List<ExchangeRequestEntity> findByRequestedMemberIdAndMemberCouponIdAndRequestMemberInsightId(UUID requestedMemberId, Long memberCouponId, UUID requestMemberInsightId);
    List<ExchangeRequestEntity> findByRequestMemberInsightIdAndRequestedInsightId(UUID requestMemberInsightId, UUID requestedInsightId);

    @Query("select er from ExchangeRequestEntity er where er.requestMemberId = :requestMemberId and er.requestedInsightId = :requestedInsightId and er.memberCouponId is not null")
    List<ExchangeRequestEntity> findByRequestMemberIdAndRequestedInsightIdAndMemberCouponIsNotNull(UUID requestMemberId, UUID requestedInsightId);
    List<ExchangeRequestEntity> findByRequestedMemberIdAndRequestMemberInsightId(UUID requestedMemberId, UUID requestMemberInsightId);

    Page<ExchangeRequestEntity> findAllByRequestMemberIdAndStatus(UUID requestMemberId, ExchangeRequestStatus status, Pageable pageable);
    Page<ExchangeRequestEntity> findAllByRequestedMemberIdAndStatus(UUID requestedMemberId, ExchangeRequestStatus status, Pageable pageable);
}
