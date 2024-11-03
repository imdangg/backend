package com.project.imdang.handler;

import com.project.imdang.InsightDomainService;
import com.project.imdang.dto.AccuseInsightCommand;
import com.project.imdang.entity.Insight;
import com.project.imdang.event.InsightAccusedEvent;
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
