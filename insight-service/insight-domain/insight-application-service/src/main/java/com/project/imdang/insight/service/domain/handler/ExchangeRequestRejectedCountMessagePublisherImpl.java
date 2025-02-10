package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.domain.message.ExchangeRequestRejectedCountRequestMessage;
import com.project.imdang.domain.message.ExchangeRequestRejectedRequestMessage;
import com.project.imdang.insight.service.domain.ports.output.publisher.ExchangeRequestRejectedCountMessagePublisher;
import com.project.imdang.insight.service.domain.ports.output.publisher.ExchangeRequestRejectedRequestMessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExchangeRequestRejectedCountMessagePublisherImpl implements ExchangeRequestRejectedCountMessagePublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(ExchangeRequestRejectedCountRequestMessage exchangeRequestRejectedCountRequestMessage) {
        applicationEventPublisher.publishEvent(exchangeRequestRejectedCountRequestMessage);
    }
}
