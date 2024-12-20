package com.project.imdang.insight.service.domain.ports.output.repository;

import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.valueobject.ExchangeRequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface ExchangeRequestRepository {

    ExchangeRequest save(ExchangeRequest exchangeRequest);
    // TODO - CHECK : 교환요청 삭제
    void deleteById(ExchangeRequestId exchangeRequestId);

    Optional<ExchangeRequest> findById(ExchangeRequestId exchangeRequestId);
    Optional<ExchangeRequest> findByRequestMemberIdAndRequestedInsightId(MemberId requestMemberId, InsightId requestedInsightId);
    Optional<ExchangeRequest> findByRequestMemberIdAndRequestedInsightIdAndExchangeRequestStatus(MemberId requestMemberId, InsightId requestedInsightId, ExchangeRequestStatus exchangeRequestStatus);

    Page<ExchangeRequest> findAllByRequestMemberIdAndExchangeRequestStatus(MemberId requestMemberId, ExchangeRequestStatus exchangeRequestStatus, PageRequest pageRequest);
    Page<ExchangeRequest> findAllByRequestedMemberIdAndExchangeRequestStatus(MemberId requestedMemberId, ExchangeRequestStatus exchangeRequestStatus, PageRequest pageRequest);
}
