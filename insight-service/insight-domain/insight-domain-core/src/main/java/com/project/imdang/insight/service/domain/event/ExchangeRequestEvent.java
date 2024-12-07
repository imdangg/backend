package com.project.imdang.insight.service.domain.event;

import com.project.imdang.domain.event.DomainEvent;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@RequiredArgsConstructor
public abstract class ExchangeRequestEvent implements DomainEvent<ExchangeRequest> {
    private final ExchangeRequest exchangeRequest;
    private final ZonedDateTime createdAt;
}
