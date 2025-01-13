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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

        // TODO : 이미지 처리
        String mainImage = uploadImage(updateInsightCommand.getMainImage());
        InsightUpdatedEvent insightUpdatedEvent = insightDomainService.updateInsight(
                insight,
                updatedBy,
                mainImage,
                updateInsightCommand.getTitle(),
                updateInsightCommand.getAddress(),
                updateInsightCommand.getApartmentComplex(),
                updateInsightCommand.getVisitAt(),
                updateInsightCommand.getVisitTimes(),
                updateInsightCommand.getVisitMethods(),
                updateInsightCommand.getAccess(),
                updateInsightCommand.getSummary(),
                updateInsightCommand.getInfra(),
                updateInsightCommand.getComplexEnvironment(),
                updateInsightCommand.getComplexFacility(),
                updateInsightCommand.getFavorableNews(),
                updateInsightCommand.getScore());
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

    private String uploadImage(MultipartFile image) {

        String filename = image.getOriginalFilename();
        if (image.isEmpty()) {
            // TODO : 예외 처리
            throw new RuntimeException();
        }

        String filePath = "uploads/" + filename;
        try {
            image.transferTo(new File(filePath));
        } catch (IOException e) {
            // TODO : 예외 처리
            throw new RuntimeException(e);
        }
        return filename;
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
