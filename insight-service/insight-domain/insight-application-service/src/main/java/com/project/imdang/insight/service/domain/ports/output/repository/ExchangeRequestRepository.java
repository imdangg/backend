package com.project.imdang.insight.service.domain.ports.output.repository;

import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;

public interface ExchangeRequestRepository {

    ExchangeRequest save(ExchangeRequest exchangeRequest);
//    ExchangeRequest update(ExchangeRequest exchangeRequest);
    // TODO - CHECK : 교환요청 삭제
    void deleteById(ExchangeRequestId exchangeRequestId);

//    Optional<ExchangeRequest> findById(ExchangeRequestId exchangeRequestId);
//    List<ExchangeRequest> findAll();
    ExchangeRequest findExchangeRequest(ExchangeRequestId requestId);
}
