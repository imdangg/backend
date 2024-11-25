package com.project.imdang.insight.service.domain.ports.output.repository;

import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;

import java.util.List;

public interface ExchangeRequestRepository {

    ExchangeRequest save(ExchangeRequest exchangeRequest);
    // TODO - CHECK : 교환요청 삭제
    void deleteById(ExchangeRequestId exchangeRequestId);

    ExchangeRequest findExchangeRequest(ExchangeRequestId requestId);

    List<ExchangeRequest> findAllByMe(MemberId memberId);

    List<ExchangeRequest> findAllByOther(MemberId memberId);
}
