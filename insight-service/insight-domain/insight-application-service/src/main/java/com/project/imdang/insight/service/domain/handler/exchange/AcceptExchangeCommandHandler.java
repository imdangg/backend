package com.project.imdang.insight.service.domain.handler.exchange;

import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.insight.service.domain.ExchangeDomainService;
import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestCommand;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.event.ExchangeRequestAcceptedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class AcceptExchangeCommandHandler {

    private final ExchangeDomainService exchangeDomainService;
    public ExchangeRequestId getExchangeRequestId(AcceptExchangeRequestCommand acceptExchangeRequestCommand) {
        return new ExchangeRequestId(UUID.fromString(acceptExchangeRequestCommand.getExchangeId()));
    }

    public ExchangeRequest acceptExchangeRequest(ExchangeRequest exchangeRequest) {
        //TODO - CHECK: exchangeDomainService를 타고 가는게 맞을지?
        exchangeRequest.accept();
        log.info("교환요청 수락 완료 {}", exchangeRequest.getStatus());

        ExchangeRequestAcceptedEvent acceptedEvent = exchangeDomainService.acceptExchangeRequest(exchangeRequest);

        //TODO : publish
        return exchangeRequest;
    }
}
