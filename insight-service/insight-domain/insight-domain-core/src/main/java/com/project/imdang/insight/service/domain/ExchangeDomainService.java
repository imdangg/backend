package com.project.imdang.insight.service.domain;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.event.ExchangeRequestAcceptedEvent;
import com.project.imdang.insight.service.domain.event.ExchangeRequestCreatedEvent;
import com.project.imdang.insight.service.domain.event.ExchangeRequestRejectedEvent;
import com.project.imdang.insight.service.domain.valueobject.SnapshotId;

public interface ExchangeDomainService {
//    ExchangeRequestCreatedEvent request(ExchangeRequest exchangeRequest);
    ExchangeRequestCreatedEvent requestExchange(ExchangeRequest exchangeRequest, Snapshot requestedSnapshot, Snapshot requestMemberSnapshot);
    ExchangeRequestAcceptedEvent acceptExchangeRequest(ExchangeRequest exchangeRequest);
    ExchangeRequestRejectedEvent rejectExchangeRequest(ExchangeRequest exchangeRequest);
}
