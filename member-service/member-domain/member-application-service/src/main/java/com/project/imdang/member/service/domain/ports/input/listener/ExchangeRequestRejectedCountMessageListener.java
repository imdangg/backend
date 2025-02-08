package com.project.imdang.member.service.domain.ports.input.listener;

import com.project.imdang.domain.message.ExchangeRequestRejectedCountRequestMessage;

public interface ExchangeRequestRejectedCountMessageListener {
    void handle(ExchangeRequestRejectedCountRequestMessage exchangeRequestRejectedCountRequestMessage);
}
