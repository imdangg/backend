package com.project.imdang.insight.service.domain.handler.exchange;

import com.project.imdang.insight.service.domain.ExchangeDomainService;
import com.project.imdang.insight.service.domain.InsightDomainService;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightCommand;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightResponse;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.event.ExchangeRequestCreatedEvent;
import com.project.imdang.insight.service.domain.exception.ExchangeDomainException;
import com.project.imdang.insight.service.domain.mapper.ExchangeRequestDataMapper;
import com.project.imdang.insight.service.domain.ports.output.repository.ExchangeRequestRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.InsightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@RequiredArgsConstructor
@Component
public class RequestExchangeCommandHandler {

    private final InsightDomainService insightDomainService;
    private final ExchangeDomainService exchangeDomainService;
    private final ExchangeRequestDataMapper exchangeRequestDataMapper;
    private final ExchangeRequestRepository exchangeRequestRepository;
    private final InsightRepository insightRepository;

    public RequestExchangeInsightResponse request(RequestExchangeInsightCommand requestExchangeInsightCommand) {
        ExchangeRequest exchangeRequest = exchangeRequestDataMapper.toExchangeRequest(requestExchangeInsightCommand);
        //1. 중복 검사
        checkDuplication(exchangeRequest);
        //2.저장
        ExchangeRequest savedExchangeRequest = save(exchangeRequest);
        //3. 알람 이벤트 생성
        ExchangeRequestCreatedEvent createdEvent = exchangeDomainService.requestExchange(exchangeRequest);
        //4. publish

        //5. 응답
        return new RequestExchangeInsightResponse(savedExchangeRequest.getId().getValue());
        //InsightAccusedEvent insightAccusedEvent = insightDomainService.accuse(insight);
    }

    private ExchangeRequest save(ExchangeRequest exchangeRequest) {
        ExchangeRequest savedExchangeRequest = exchangeRequestRepository.save(exchangeRequest);
        if(savedExchangeRequest == null) {
            String errorMessage = "Could not save ExchangeRequest!";
            log.error(errorMessage);
            throw new ExchangeDomainException(errorMessage);
        }
        log.info("ExchangeRequest[id: {}] is saved.", savedExchangeRequest.getId().getValue());
        return savedExchangeRequest;
    }
    private void checkDuplication(ExchangeRequest exchangeRequest) {
        // TODO : SnapShot 조회
       exchangeRequestRepository.exist(exchangeRequest.getRequestMemberId(), exchangeRequest.getRequestedInsightId());
    }

    private void checkInsight(ExchangeRequest exchangeRequest) {

    }
}
