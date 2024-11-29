package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.insight.service.domain.InsightDomainService;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightResponse;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.exception.InsightDomainException;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.service.domain.ports.output.repository.InsightRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.SnapshotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreateInsightCommandHandler {

    private final InsightDomainService insightDomainService;
    private final InsightRepository insightRepository;
    private final SnapshotRepository snapshotRepository;

    private final InsightDataMapper insightDataMapper;

    @Transactional
    public CreateInsightResponse createInsight(CreateInsightCommand createInsightCommand) {
        Insight insight = insightDataMapper.createInsightCommandToInsight(createInsightCommand);
        // TODO - CHECK : event
        Insight created = insightDomainService.createInsight(insight);
        saveInsight(created);
        log.info("Insight[id: {}] is created.", created.getId().getValue());
        // TODO : event
//        Snapshot snapshot = created.capture();
//        saveSnapshot(snapshot);
        return insightDataMapper.insightToCreateInsightResponse(insight);
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
