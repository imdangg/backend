package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.InsightDomainService;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightResponse;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.entity.MemberSnapshot;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.exception.InsightDomainException;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.service.domain.ports.output.repository.InsightRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.MemberSnapshotRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.SnapshotRepository;
import com.project.imdang.insight.service.domain.valueobject.SnapshotId;
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
    private final MemberSnapshotRepository memberSnapshotRepository;

    private final InsightDataMapper insightDataMapper;

    @Transactional
    public CreateInsightResponse createInsight(CreateInsightCommand createInsightCommand) {
        Insight insight = insightDataMapper.createInsightCommandToInsight(createInsightCommand);
        // TODO - CHECK : event
        Insight created = insightDomainService.createInsight(insight);
        Insight saved = saveInsight(created);
        log.info("Insight[id: {}] is created.", saved.getId().getValue());

        // TODO : event
        // TODO : InsightCreatedEvent
        Snapshot snapshot = insightDomainService.captureInsight(saved);
        // TODO : snapshotDomainService(?)
        Snapshot savedSnapshot = saveSnapshot(snapshot);
        // memberSnapshot에 insert
        MemberSnapshot memberSnapshot = MemberSnapshot.builder()
                .memberId(new MemberId(createInsightCommand.getMemberId()))
                .snapshotId(new SnapshotId(savedSnapshot.getId().getValue()))
                .insightId(new InsightId(savedSnapshot.getInsightId().getValue()))
                .createdAt(created.getCreatedAt())
                .build();
        saveMemberSnapshot(memberSnapshot);

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

    private void saveMemberSnapshot(MemberSnapshot memberSnapshot) {
        MemberSnapshot saved = memberSnapshotRepository.save(memberSnapshot);
        if (saved == null) {
            String errorMessage = "Could not save memberSnapshot!";
            log.error(errorMessage);
            throw new InsightDomainException(errorMessage);
        }
        log.info("memberSnapshot[id: {}] is saved.", saved.getId().getValue());
    }
}
