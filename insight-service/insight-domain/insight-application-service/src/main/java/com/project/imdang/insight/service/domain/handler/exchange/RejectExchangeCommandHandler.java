package com.project.imdang.insight.service.domain.handler.exchange;

import com.project.imdang.insight.service.domain.ExchangeDomainService;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestCommand;
import com.project.imdang.insight.service.domain.dto.exchange.reject.RejectExchangeRequestResponse;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.event.ExchangeRequestRejectedEvent;
import com.project.imdang.insight.service.domain.exception.ExchangeDomainException;
import com.project.imdang.insight.service.domain.exception.ExchangeRequestNotFoundException;
import com.project.imdang.insight.service.domain.ports.output.repository.ExchangeRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class RejectExchangeCommandHandler {
    // TODO - CHECK : 교환 요청/거절/승인 내역을 따로 저장해서 GROUP BY로 COUNT하는 방법
    // TODO - CHECK : 거절 횟수로 쿠폰 발급하는 것은 배치로? 비동기(kafka)? 로직에서 바로 처리
// 교환 요청한 상대방의 rejectedCount + 1 → 횟수 비교해서 쿠폰 발급
    private final ExchangeDomainService exchangeDomainService;
    private final ExchangeRequestRepository exchangeRequestRepository;

    public RejectExchangeRequestResponse rejectExchangeRequest(RejectExchangeRequestCommand rejectExchangeRequestCommand) {
        UUID exchangeRequestId = rejectExchangeRequestCommand.getExchangeRequestId();
        ExchangeRequest exchangeRequest = checkExchangeRequest(exchangeRequestId);
        //1. 요청 거절
        ExchangeRequestRejectedEvent rejectedEvent = exchangeDomainService.rejectExchangeRequest(exchangeRequest);
        log.info("ExchangeRequest[id: {}] is rejected.", exchangeRequest.getId().getValue());
        ExchangeRequest saveExchangeRequest = save(exchangeRequest);

        //TODO : 2. publish
        return new RejectExchangeRequestResponse(saveExchangeRequest.getRequestedInsightId().getValue());
    }

    private ExchangeRequest checkExchangeRequest(UUID exchangeRequestId) {
        return exchangeRequestRepository.find(exchangeRequestId)
                .orElseThrow(() -> new ExchangeRequestNotFoundException("Could not found ExchangeRequest"));
    }

    private ExchangeRequest save(ExchangeRequest exchangeRequest) {
        ExchangeRequest savedExchangeRequest = exchangeRequestRepository.save(exchangeRequest);
        if(savedExchangeRequest == null) {
            String errorMessage = "Could not save ExchangeRequest!";
            log.error(errorMessage);
            throw new ExchangeDomainException(errorMessage);
        }
        log.info("RequestRequest[id: {}] is saved.", savedExchangeRequest.getId().getValue());
        return savedExchangeRequest;
    }

}
