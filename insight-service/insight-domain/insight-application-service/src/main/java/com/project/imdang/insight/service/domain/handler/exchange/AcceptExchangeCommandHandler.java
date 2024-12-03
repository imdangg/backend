package com.project.imdang.insight.service.domain.handler.exchange;

import com.project.imdang.insight.service.domain.ExchangeDomainService;
import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.accept.AcceptExchangeRequestResponse;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.event.ExchangeRequestAcceptedEvent;
import com.project.imdang.insight.service.domain.exception.ExchangeDomainException;
import com.project.imdang.insight.service.domain.exception.ExchangeNotFoundException;
import com.project.imdang.insight.service.domain.ports.output.repository.ExchangeRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class AcceptExchangeCommandHandler {

    private final ExchangeDomainService exchangeDomainService;
    private final ExchangeRequestRepository exchangeRequestRepository;


    public AcceptExchangeRequestResponse acceptExchangeRequest(AcceptExchangeRequestCommand acceptExchangeRequestCommand) {
        UUID exchangeRequestId = acceptExchangeRequestCommand.getExchangeRequestId();
        ExchangeRequest exchangeRequest = checkExchangeRequest(exchangeRequestId);
        //1. 요청 수락
        ExchangeRequestAcceptedEvent acceptedEvent = exchangeDomainService.acceptExchangeRequest(exchangeRequest);
        log.info("Exchange[id: {}] is accepted!", exchangeRequest.getId().getValue());
        ExchangeRequest saveExchangeRequest = save(exchangeRequest);
        //TODO : 2. publish

        return new AcceptExchangeRequestResponse(saveExchangeRequest.getId().getValue());
    }

    private ExchangeRequest checkExchangeRequest(UUID exchangeRequestId) {
        return exchangeRequestRepository.find(exchangeRequestId)
                .orElseThrow(() -> new ExchangeNotFoundException("Could not found ExchangeRequest"));
    }

    private ExchangeRequest save(ExchangeRequest exchangeRequest) {
        ExchangeRequest savedExchangeRequest = exchangeRequestRepository.save(exchangeRequest);
        if(savedExchangeRequest == null) {
            String errorMessage = "ExchangeReqeust save Failed!";
            log.error(errorMessage);
            throw new ExchangeDomainException(errorMessage);
        }
        log.info("RequestId[id: {}] is Saved", savedExchangeRequest.getId().getValue());
        return savedExchangeRequest;
    }
}
