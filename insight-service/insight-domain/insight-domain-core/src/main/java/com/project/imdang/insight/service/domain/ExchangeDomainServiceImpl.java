package com.project.imdang.insight.service.domain;

import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.event.ExchangeRequestAcceptedEvent;
import com.project.imdang.insight.service.domain.event.ExchangeRequestCreatedEvent;
import com.project.imdang.insight.service.domain.event.ExchangeRequestRejectedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;

@Slf4j
public class ExchangeDomainServiceImpl implements ExchangeDomainService {

    @Override
    public ExchangeRequestCreatedEvent requestExchange(ExchangeRequest exchangeRequest) {
        exchangeRequest.initialize();
        log.info("ExchangeRequest[id: {}] is initialized.", exchangeRequest.getId().getValue());
        return new ExchangeRequestCreatedEvent(exchangeRequest, ZonedDateTime.now());
    }

    @Override
    public ExchangeRequestAcceptedEvent acceptExchangeRequest(ExchangeRequest exchangeRequest) {
        exchangeRequest.accept();
        log.info("ExchangeRequest[id: {}] is accepted.", exchangeRequest.getId().getValue());
        return new ExchangeRequestAcceptedEvent(exchangeRequest, ZonedDateTime.now());
    }

    @Override
    public ExchangeRequestRejectedEvent rejectExchangeRequest(ExchangeRequest exchangeRequest) {
        exchangeRequest.reject();
        log.info("ExchangeRequest[id: {}] is rejected.", exchangeRequest.getId().getValue());
        // TODO : 요청한 사용자의 거절 카운트 + 1, 거절 알림
        return new ExchangeRequestRejectedEvent(exchangeRequest, ZonedDateTime.now());
    }
}
