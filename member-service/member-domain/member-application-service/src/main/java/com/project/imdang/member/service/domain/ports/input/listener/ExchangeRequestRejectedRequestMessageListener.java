package com.project.imdang.member.service.domain.ports.input.listener;

import com.project.imdang.domain.message.ExchangeRequestRejectedRequestMessage;

public interface ExchangeRequestRejectedRequestMessageListener {

    void handle(ExchangeRequestRejectedRequestMessage exchangeRequestRejectedRequestMessage);
}
