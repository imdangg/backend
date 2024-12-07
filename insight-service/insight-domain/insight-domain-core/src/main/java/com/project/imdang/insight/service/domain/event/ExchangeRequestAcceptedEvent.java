package com.project.imdang.insight.service.domain.event;

import com.project.imdang.insight.service.domain.entity.ExchangeRequest;

import java.time.ZonedDateTime;

public class ExchangeRequestAcceptedEvent extends ExchangeRequestEvent {

    public ExchangeRequestAcceptedEvent(ExchangeRequest exchangeRequest, ZonedDateTime createdAt) {
        super(exchangeRequest, createdAt);
    }
}
