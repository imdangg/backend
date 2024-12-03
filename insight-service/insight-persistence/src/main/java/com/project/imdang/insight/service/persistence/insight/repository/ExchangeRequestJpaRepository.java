package com.project.imdang.insight.service.persistence.insight.repository;

import com.project.imdang.insight.service.persistence.insight.entity.ExchangeRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExchangeRequestJpaRepository extends JpaRepository<ExchangeRequestEntity, UUID> {
    List<ExchangeRequestEntity> findAllByRequestMemberId(UUID memberId);

    @Query("select ex from ExchangeRequestEntity ex inner join InsightEntity i on i.memberId = :memberId and ex.requestedInsightId = i.id")
    List<ExchangeRequestEntity> findAllOtherByRequestMemberId(UUID memberId);
}
