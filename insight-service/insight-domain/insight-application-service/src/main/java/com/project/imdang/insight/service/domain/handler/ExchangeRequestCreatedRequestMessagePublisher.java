package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.domain.message.ExchangeRequestCreatedRequestMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExchangeRequestCreatedRequestMessagePublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(ExchangeRequestCreatedRequestMessage exchangeRequestCreatedRequestMessage) {
        applicationEventPublisher.publishEvent(exchangeRequestCreatedRequestMessage);
    }
}
