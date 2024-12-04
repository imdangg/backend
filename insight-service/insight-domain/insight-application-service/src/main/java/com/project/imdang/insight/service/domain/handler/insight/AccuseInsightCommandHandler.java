package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.insight.service.domain.InsightDomainService;
import com.project.imdang.insight.service.domain.dto.insight.accuse.AccuseInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.accuse.AccuseInsightResponse;
import com.project.imdang.insight.service.domain.entity.Accuse;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.event.InsightAccusedEvent;
import com.project.imdang.insight.service.domain.exception.InsightDomainException;
import com.project.imdang.insight.service.domain.exception.InsightNotFoundException;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.service.domain.ports.output.repository.AccuseRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.InsightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class AccuseInsightCommandHandler {
// TODO - 배치
// 상대방의 신고 횟수 + 1
    private final InsightDomainService insightDomainService;
    private final InsightRepository insightRepository;
    private final AccuseRepository accuseRepository;

    private final InsightDataMapper insightDataMapper;

    @Transactional
    public AccuseInsightResponse accuseInsight(AccuseInsightCommand accuseInsightCommand) {
        Insight insight = checkInsight(accuseInsightCommand.getInsightId());
        InsightAccusedEvent insightAccusedEvent = insightDomainService.accuseInsight(insight);
        log.info("Insight[id: {}] is accused.", insightAccusedEvent.getInsight().getId().getValue());
        // TODO - 신고 횟수에 따른 이벤트 발생
        // TODO - publish event
//        saveAccuse(accuse);
        return insightDataMapper.insightToAccuseInsightResponse(insightAccusedEvent.getInsight());
    }

    private Insight checkInsight(UUID _insightId) {
        InsightId insightId = new InsightId(_insightId);
        Optional<Insight> insightResult = insightRepository.findInsight(insightId);
        if (insightResult.isEmpty()) {
            throw new InsightNotFoundException(insightId);
        }
        return insightResult.get();
    }

    private void saveAccuse(Accuse accuse) {
        Accuse saved = accuseRepository.save(accuse);
        if (saved == null) {
            String errorMessage = "Could not save accuse!";
            log.error(errorMessage);
            // TODO - CHECK : AccuseDomainException
            throw new InsightDomainException(errorMessage);
        }
        log.info("Accuse[id: {}] is saved.", saved.getId().getValue());
    }
}
