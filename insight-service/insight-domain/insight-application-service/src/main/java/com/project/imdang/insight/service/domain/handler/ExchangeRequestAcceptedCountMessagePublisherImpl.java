package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.domain.message.ExchangeRequestAcceptedCountRequestMessage;
import com.project.imdang.insight.service.domain.ports.output.publisher.ExchangeRequestAcceptedCountMessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExchangeRequestAcceptedCountMessagePublisherImpl implements ExchangeRequestAcceptedCountMessagePublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(ExchangeRequestAcceptedCountRequestMessage exchangeRequestCountRequestMessage) {
        applicationEventPublisher.publishEvent(exchangeRequestCountRequestMessage);
    }
}
