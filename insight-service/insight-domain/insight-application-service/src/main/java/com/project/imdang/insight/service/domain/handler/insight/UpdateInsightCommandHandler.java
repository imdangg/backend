package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
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
import com.project.imdang.insight.service.domain.ports.output.repository.MemberSnapshotRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.SnapshotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class UpdateInsightCommandHandler {
    private final InsightDomainService insightDomainService;
    private final InsightRepository insightRepository;
    private final SnapshotRepository snapshotRepository;
    private final MemberSnapshotRepository memberSnapshotRepository;

    private final InsightDataMapper insightDataMapper;

    @Transactional
    public UpdateInsightResponse updateInsight(UpdateInsightCommand updateInsightCommand) {
        InsightId insightId = new InsightId(updateInsightCommand.getInsightId());

        // validation check
        MemberId updatedBy = new MemberId(updateInsightCommand.getMemberId());
        Insight insight = checkInsight(insightId);
        InsightUpdatedEvent insightUpdatedEvent = insightDomainService.updateInsight(
                insight,
                updatedBy,
                updateInsightCommand.getScore(),
                updateInsightCommand.getTitle(),
                updateInsightCommand.getContents(),
                updateInsightCommand.getMainImage(),
                updateInsightCommand.getSummary(),
                updateInsightCommand.getVisitAt(),
                updateInsightCommand.getVisitMethod(),
                updateInsightCommand.getAccess(),
                updateInsightCommand.getInfra(),
                updateInsightCommand.getComplexEnvironment(),
                updateInsightCommand.getComplexFacility(),
                updateInsightCommand.getFavorableNews());
        Insight updated = insightUpdatedEvent.getInsight();
        log.info("Insight[id: {}] is updated.", updated.getId().getValue());
        saveInsight(updated);

        Snapshot snapshot = insightDomainService.captureInsight(insightUpdatedEvent.getInsight());
        Snapshot saved = saveSnapshot(snapshot);

        // memberSnapshot에 update
        memberSnapshotRepository.updateSnapshotIdByMemberIdAndInsightId(saved.getId(), updatedBy, insightId);

        return insightDataMapper.insightToUpdateInsightResponse(insightUpdatedEvent.getInsight());
    }

    private Insight checkInsight(InsightId insightId) {
        Optional<Insight> insightResult = insightRepository.findById(insightId);
        if (insightResult.isEmpty()) {
            throw new InsightNotFoundException(insightId);
        }
        return insightResult.get();
    }

    private Insight saveInsight(Insight insight) {
        Insight saved = insightRepository.save(insight);
        if (saved == null) {
            String errorMessage = "Could not save insight!";
            log.error(errorMessage);
            throw new InsightDomainException(errorMessage);
        }
        log.info("Insight[id: {}] is saved.", saved.getId().getValue());
        return saved;
    }

    private Snapshot saveSnapshot(Snapshot snapshot) {
        Snapshot saved = snapshotRepository.save(snapshot);
        if (saved == null) {
            String errorMessage = "Could not save snapshot!";
            log.error(errorMessage);
            throw new InsightDomainException(errorMessage);
        }
        log.info("Snapshot[id: {}] is saved.", saved.getId().getValue());
        return saved;
    }
}
