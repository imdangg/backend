package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.insight.service.domain.InsightDomainService;
import com.project.imdang.insight.service.domain.dto.AccuseInsightCommand;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.event.InsightAccusedEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
//@Component
public class AccuseInsightCommandHandler {

    private final InsightDomainService insightDomainService;
//    private final InsightRepository insightRepository;

//    @Transactional
    public void accuse(AccuseInsightCommand accuseInsightCommand) {
        Insight insight = checkInsight();
        InsightAccusedEvent insightAccusedEvent = insightDomainService.accuse(insight);
        // TODO - publish
    }

    private Insight checkInsight() {
        return null;
    }

}
