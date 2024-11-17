package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.insight.service.domain.InsightDomainService;
import com.project.imdang.insight.service.domain.dto.insight.accuse.AccuseInsightCommand;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.event.InsightAccusedEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
//@Component
public class AccuseInsightCommandHandler {
// TODO - 배치
// 상대방의 신고 횟수 + 1
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