package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.insight.service.domain.InsightDomainService;
import com.project.imdang.insight.service.domain.dto.insight.update.UpdateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.update.UpdateInsightResponse;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.event.InsightUpdatedEvent;
import com.project.imdang.insight.service.domain.exception.InsightDomainException;
import com.project.imdang.insight.service.domain.exception.InsightNotFoundException;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.service.domain.ports.output.repository.InsightRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.SnapshotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class UpdateInsightCommandHandler {
    private final InsightDomainService insightDomainService;
    private final InsightRepository insightRepository;
    private final SnapshotRepository snapshotRepository;

    private final InsightDataMapper insightDataMapper;

    @Transactional
    public UpdateInsightResponse updateInsight(UpdateInsightCommand updateInsightCommand) {
        UUID insightId = updateInsightCommand.getInsightId();
        Insight insight = checkInsight(insightId);
        InsightUpdatedEvent insightUpdatedEvent = insightDomainService.updateInsight(
                insight,
                updateInsightCommand.getScore(),
                updateInsightCommand.getTitle(),
                updateInsightCommand.getContents(),
                updateInsightCommand.getImages(),
                updateInsightCommand.getSummary(),
                updateInsightCommand.getVisitAt(),
                updateInsightCommand.getVisitMethod(),
                updateInsightCommand.getAccess(),
                updateInsightCommand.getInfra(),
                updateInsightCommand.getComplexEnvironment(),
                updateInsightCommand.getComplexFacility(),
                updateInsightCommand.getFavorableNews());
        log.info("Insight[id: {}] is updated.", insightUpdatedEvent.getInsight().getId().getValue());

        Snapshot snapshot = insightDomainService.captureInsight(insightUpdatedEvent.getInsight());
        saveSnapshot(snapshot);
        return insightDataMapper.insightToUpdateInsightResponse(insightUpdatedEvent.getInsight());
    }

    private Insight checkInsight(UUID _insightId) {
        InsightId insightId = new InsightId(_insightId);
        Optional<Insight> insightResult = insightRepository.findById(insightId);
        if (insightResult.isEmpty()) {
            throw new InsightNotFoundException(insightId);
        }
        return insightResult.get();
    }

    private void saveSnapshot(Snapshot snapshot) {
        Snapshot saved = snapshotRepository.save(snapshot);
        if (saved == null) {
            String errorMessage = "Could not save snapshot!";
            log.error(errorMessage);
            throw new InsightDomainException(errorMessage);
        }
        log.info("Snapshot[id: {}] is saved.", saved.getId().getValue());
    }
}
