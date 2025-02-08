package com.project.imdang.insight.service.domain.ports.output.publisher;

import com.project.imdang.domain.message.ExchangeRequestAcceptedCountRequestMessage;

public interface ExchangeRequestAcceptedCountMessagePublisher {
    void publish(ExchangeRequestAcceptedCountRequestMessage exchangeRequestCountRequestMessage);
}
