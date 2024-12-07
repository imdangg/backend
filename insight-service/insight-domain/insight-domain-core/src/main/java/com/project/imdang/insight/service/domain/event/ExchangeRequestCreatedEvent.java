package com.project.imdang.insight.service.domain.event;

import com.project.imdang.insight.service.domain.entity.ExchangeRequest;

import java.time.ZonedDateTime;

public class ExchangeRequestCreatedEvent extends ExchangeRequestEvent {

    public ExchangeRequestCreatedEvent(ExchangeRequest exchangeRequest, ZonedDateTime createdAt) {
        super(exchangeRequest, createdAt);
    }
}
