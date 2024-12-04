package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.insight.service.domain.InsightDomainService;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightResponse;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.event.InsightDeletedEvent;
import com.project.imdang.insight.service.domain.exception.InsightNotFoundException;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
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
public class DeleteInsightCommandHandler {
    private final InsightDomainService insightDomainService;
    private final InsightRepository insightRepository;
    private final InsightDataMapper insightDataMapper;

    @Transactional
    public DeleteInsightResponse deleteInsight(DeleteInsightCommand deleteInsightCommand) {
        Insight insight = checkInsight(deleteInsightCommand.getInsightId());
        InsightDeletedEvent insightDeletedEvent = insightDomainService.deleteInsight(insight);
        deleteInsight(insightDeletedEvent.getInsight().getId());
        log.info("Insight[id: {}] is deleted.", insightDeletedEvent.getInsight().getId().getValue());
        // TODO - publish event
        return insightDataMapper.insightToDeleteInsightResponse(insightDeletedEvent.getInsight());
    }

    private Insight checkInsight(UUID _insightId) {
        InsightId insightId = new InsightId(_insightId);
        Optional<Insight> insightResult = insightRepository.findInsight(insightId);
        if (insightResult.isEmpty()) {
            throw new InsightNotFoundException(insightId);
        }
        return insightResult.get();
    }

    private void deleteInsight(InsightId insightId) {
        insightRepository.deleteInsight(insightId);
    }
}
