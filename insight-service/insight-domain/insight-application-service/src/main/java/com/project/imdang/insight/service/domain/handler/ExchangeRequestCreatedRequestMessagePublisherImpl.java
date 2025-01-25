package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.domain.message.ExchangeRequestCreatedRequestMessage;
import com.project.imdang.insight.service.domain.ports.output.publisher.ExchangeRequestCreatedRequestMessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExchangeRequestCreatedRequestMessagePublisherImpl implements ExchangeRequestCreatedRequestMessagePublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(ExchangeRequestCreatedRequestMessage exchangeRequestCreatedRequestMessage) {
        applicationEventPublisher.publishEvent(exchangeRequestCreatedRequestMessage);
    }
}
