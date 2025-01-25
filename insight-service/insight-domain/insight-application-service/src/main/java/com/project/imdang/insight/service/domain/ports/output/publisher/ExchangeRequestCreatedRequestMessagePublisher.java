package com.project.imdang.insight.service.domain.ports.output.publisher;

import com.project.imdang.domain.message.ExchangeRequestCreatedRequestMessage;

public interface ExchangeRequestCreatedRequestMessagePublisher {
    void publish(ExchangeRequestCreatedRequestMessage exchangeRequestCreatedRequestMessage);
}
