package com.project.imdang.member.service.domain.ports.input.listener;

import com.project.imdang.domain.message.ExchangeRequestCreatedRequestMessage;

public interface ExchangeRequestCreatedRequestMessageListener {

    void handle(ExchangeRequestCreatedRequestMessage exchangeRequestCreatedRequestMessage);
}
