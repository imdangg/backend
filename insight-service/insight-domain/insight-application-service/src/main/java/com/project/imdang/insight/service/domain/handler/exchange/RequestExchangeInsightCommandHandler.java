package com.project.imdang.insight.service.domain.handler.exchange;

import com.project.imdang.insight.service.domain.InsightDomainService;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightCommand;
import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightResponse;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.domain.valueobject.InsightId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class RequestExchangeInsightCommandHandler {

    private final InsightDomainService insightDomainService;
//    private final InsightRepository insightRepository;

    @Transactional
    public RequestExchangeInsightResponse requestExchangeInsight(RequestExchangeInsightCommand requestExchangeInsightCommand) {
//        InsightId requestedInsightId = new InsightId(requestExchangeInsightCommand.getRequestedInsightId());
//        Insight insight = checkInsight(requestedInsightId);


//        InsightAccusedEvent insightAccusedEvent = insightDomainService.accuse(insight);
        // TODO - publish
        return null;
    }

    private Insight checkInsight(InsightId insightId) {
        return null;
    }

}
