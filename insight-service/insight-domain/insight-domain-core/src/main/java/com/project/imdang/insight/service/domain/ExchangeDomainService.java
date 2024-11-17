package com.project.imdang.insight.service.domain;

import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.event.ExchangeRequestAcceptedEvent;
import com.project.imdang.insight.service.domain.event.ExchangeRequestCreatedEvent;
import com.project.imdang.insight.service.domain.event.ExchangeRequestRejectedEvent;

public interface ExchangeDomainService {
    ExchangeRequestCreatedEvent requestExchange(ExchangeRequest exchangeRequest);
    ExchangeRequestAcceptedEvent acceptExchangeRequest(ExchangeRequest exchangeRequest);
    ExchangeRequestRejectedEvent rejectExchangeRequest(ExchangeRequest exchangeRequest);
}
