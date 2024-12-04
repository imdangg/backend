package com.project.imdang.insight.service.domain.ports.output.repository;

import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;

import java.util.List;
import java.util.Optional;

public interface ExchangeRequestRepository {

    ExchangeRequest save(ExchangeRequest exchangeRequest);
    // TODO - CHECK : 교환요청 삭제
    void deleteById(ExchangeRequestId exchangeRequestId);

    // TODO - REVIEW
    Optional<ExchangeRequest> findById(ExchangeRequestId exchangeRequestId);
    Optional<ExchangeRequest> findByRequestMemberIdAndRequestedInsightId(MemberId requestMemberId, InsightId requestedInsightId);

    List<ExchangeRequest> findAllByRequestMemberId(MemberId requestMemberId);
    List<ExchangeRequest> findAllByRequestedMemberId(MemberId requestedMemberId);
}
