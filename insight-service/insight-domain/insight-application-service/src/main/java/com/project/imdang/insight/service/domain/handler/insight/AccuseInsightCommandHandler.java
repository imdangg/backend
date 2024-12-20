package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
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
        MemberId accusedBy = new MemberId(accuseInsightCommand.getMemberId());

        // TODO - CHECK : insightId - memberId(accusedBy)로 중복 신고 여부 체크
        
        InsightAccusedEvent insightAccusedEvent = insightDomainService.accuseInsight(insight, accusedBy);
        saveInsight(insightAccusedEvent.getInsight());
        // TODO - 신고 횟수에 따른 이벤트 발생 (+ 어디서 accuse를 저장할까?)
        // TODO - publish event
        saveAccuse(insightAccusedEvent.getAccuse());

        log.info("Insight[id: {}] is accused by Member[id: {}].", insightAccusedEvent.getInsight().getId().getValue(), insightAccusedEvent.getAccuse().getAccuseMemberId().getValue());
        return insightDataMapper.insightToAccuseInsightResponse(insightAccusedEvent.getInsight());
    }

    private Insight checkInsight(UUID _insightId) {
        InsightId insightId = new InsightId(_insightId);
        Optional<Insight> insightResult = insightRepository.findById(insightId);
        if (insightResult.isEmpty()) {
            throw new InsightNotFoundException(insightId);
        }
        return insightResult.get();
    }

    private void saveInsight(Insight insight) {
        Insight saved = insightRepository.save(insight);
        if (saved == null) {
            String errorMessage = "Could not save insight!";
            log.error(errorMessage);
            throw new InsightDomainException(errorMessage);
        }
        log.info("Insight[id: {}] is saved.", saved.getId().getValue());
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
