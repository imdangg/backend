package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.domain.message.ExchangeRequestCreatedRequestMessage;

public interface ExchangeRequestCreatedRequestMessageListener {

    void handle(ExchangeRequestCreatedRequestMessage exchangeRequestCreatedRequestMessage);
}
