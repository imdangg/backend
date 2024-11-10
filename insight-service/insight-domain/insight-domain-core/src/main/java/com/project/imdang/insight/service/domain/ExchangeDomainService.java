package com.project.imdang.insight.service.domain;

import com.project.imdang.insight.service.domain.entity.Exchange;
import com.project.imdang.insight.service.domain.event.ExchangeRequestAcceptedEvent;
import com.project.imdang.insight.service.domain.event.ExchangeRequestCreatedEvent;
import com.project.imdang.insight.service.domain.event.ExchangeRequestRejectedEvent;

public interface ExchangeDomainService {
    ExchangeRequestCreatedEvent requestExchange(Exchange exchange);
    ExchangeRequestAcceptedEvent acceptExchangeRequest(Exchange exchange);
    ExchangeRequestRejectedEvent rejectExchangeRequest(Exchange exchange);
}
