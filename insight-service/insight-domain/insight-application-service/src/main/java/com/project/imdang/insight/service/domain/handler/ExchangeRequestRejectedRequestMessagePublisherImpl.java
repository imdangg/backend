package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.domain.message.ExchangeRequestRejectedRequestMessage;
import com.project.imdang.insight.service.domain.ports.output.publisher.ExchangeRequestRejectedRequestMessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExchangeRequestRejectedRequestMessagePublisherImpl implements ExchangeRequestRejectedRequestMessagePublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(ExchangeRequestRejectedRequestMessage exchangeRequestRejectedRequestMessage) {
        applicationEventPublisher.publishEvent(exchangeRequestRejectedRequestMessage);
    }
}
