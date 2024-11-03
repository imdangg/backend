package com.project.imdang.handler;

import com.project.imdang.InsightDomainService;
import com.project.imdang.dto.RequestExchangeInsightCommand;
import com.project.imdang.entity.Insight;
import com.project.imdang.event.InsightAccusedEvent;
import com.project.imdang.valueobject.InsightId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
//@Component
public class RequestExchangeInsightCommandHandler {

    private final InsightDomainService insightDomainService;
//    private final InsightRepository insightRepository;

//    @Transactional
    public void requestExchange(RequestExchangeInsightCommand requestExchangeInsightCommand) {
        InsightId requestedInsightId = new InsightId(requestExchangeInsightCommand.getRequestedInsightId());
        Insight insight = checkInsight(requestedInsightId);


        InsightAccusedEvent insightAccusedEvent = insightDomainService.accuse(insight);
        // TODO - publish
    }

    private Insight checkInsight(InsightId insightId) {
        return null;
    }

}
