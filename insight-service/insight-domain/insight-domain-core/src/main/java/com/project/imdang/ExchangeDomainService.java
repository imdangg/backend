package com.project.imdang;

import com.project.imdang.entity.Exchange;
import com.project.imdang.event.ExchangeRequestAcceptedEvent;
import com.project.imdang.event.ExchangeRequestCreatedEvent;
import com.project.imdang.event.ExchangeRequestRejectedEvent;

public interface ExchangeDomainService {
    ExchangeRequestCreatedEvent requestExchange(Exchange exchange);
    ExchangeRequestAcceptedEvent acceptExchangeRequest(Exchange exchange);
    ExchangeRequestRejectedEvent rejectExchangeRequest(Exchange exchange);
}
