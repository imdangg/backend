package com.project.imdang.insight.service.domain.ports.output.publisher;

import com.project.imdang.domain.message.ExchangeRequestRejectedRequestMessage;

public interface ExchangeRequestRejectedRequestMessagePublisher {
    void publish(ExchangeRequestRejectedRequestMessage exchangeRequestRejectedRequestMessage);
}
