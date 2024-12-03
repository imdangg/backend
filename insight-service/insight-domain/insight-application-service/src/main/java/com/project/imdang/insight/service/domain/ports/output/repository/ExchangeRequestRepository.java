package com.project.imdang.insight.service.domain.ports.output.repository;

import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExchangeRequestRepository {

    ExchangeRequest save(ExchangeRequest exchangeRequest);
    // TODO - CHECK : 교환요청 삭제
    void deleteById(ExchangeRequestId exchangeRequestId);

    Optional<ExchangeRequest> find(UUID exchangeRequestId);

    List<ExchangeRequest> findAllByMe(MemberId memberId);

    List<ExchangeRequest> findAllByOther(MemberId memberId);

    boolean exist(MemberId memberId, InsightId insightId);
}
