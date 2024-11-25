package com.project.imdang.insight.service.domain.handler.exchange;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.InsightDomainService;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightCommand;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.domain.valueobject.InsightId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class RequestExchangeCommandHandler {

    private final InsightDomainService insightDomainService;

    public ExchangeRequest request(RequestExchangeInsightCommand requestExchangeInsightCommand) {
        InsightId requestedInsightId = new InsightId(UUID.fromString(requestExchangeInsightCommand.getRequestedInsightId()));
        InsightId requestMemberInsightId = new InsightId(UUID.fromString(requestExchangeInsightCommand.getRequestMemberInsightId()));
        MemberId requestMemberId = new MemberId(UUID.fromString(requestExchangeInsightCommand.getRequestMemberInsightId()));
        // TODO - CHECK : 동일 글 교환 요청 중복 검사를 해야할까?
        ExchangeRequest exchangeRequest = new ExchangeRequest(requestMemberId, requestMemberInsightId, requestedInsightId);
        log.info("-----------");
        log.info("새로운 인사이트 교환 요청이 생성되었습니다.");
        log.info("Request ID {}", exchangeRequest.getId().getValue());
        log.info("Request CreateAt {}", exchangeRequest.getRequestedAt());
        log.info("-----------");

        //TODO - publish
        //Insight insight = checkInsight(requestedInsightId);
        //InsightAccusedEvent insightAccusedEvent = insightDomainService.accuse(insight);

        //TODO : 1. 알람 이벤트 생성
        return exchangeRequest;
    }
}
