package com.project.imdang.insight.service.domain.ports.output.publisher;

import com.project.imdang.domain.message.ExchangeRequestRejectedCountRequestMessage;
import com.project.imdang.domain.message.ExchangeRequestRejectedRequestMessage;

public interface ExchangeRequestRejectedCountMessagePublisher {
    void publish(ExchangeRequestRejectedCountRequestMessage exchangeRequestRejectedCountRequestMessage);
}
