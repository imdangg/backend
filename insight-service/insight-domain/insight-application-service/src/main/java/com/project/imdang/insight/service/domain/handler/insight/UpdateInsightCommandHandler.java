package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.InsightDomainService;
import com.project.imdang.insight.service.domain.dto.insight.update.UpdateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.update.UpdateInsightResponse;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.event.InsightUpdatedEvent;
import com.project.imdang.insight.service.domain.handler.InsightHelper;
import com.project.imdang.insight.service.domain.handler.SnapshotHelper;
import com.project.imdang.insight.service.domain.handler.UploadMultipartFileHelper;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.service.domain.ports.output.repository.MemberSnapshotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class UpdateInsightCommandHandler {
    private final InsightDomainService insightDomainService;
    private final InsightHelper insightHelper;
    private final InsightDataMapper insightDataMapper;

    private final SnapshotHelper snapshotHelper;
    private final MemberSnapshotRepository memberSnapshotRepository;

    private final UploadMultipartFileHelper uploadMultipartFileHelper;


    @Transactional
    public UpdateInsightResponse updateInsight(UpdateInsightCommand updateInsightCommand) {
        InsightId insightId = new InsightId(updateInsightCommand.getInsightId());

        // validation check
        MemberId updatedBy = new MemberId(updateInsightCommand.getMemberId());
        Insight insight = insightHelper.get(insightId);

        // TODO : 이미지 처리
        String mainImage = uploadMultipartFileHelper.uploadFile(updateInsightCommand.getMainImage());
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
        insightHelper.save(updated);

        Snapshot snapshot = insightDomainService.captureInsight(insightUpdatedEvent.getInsight());
        Snapshot saved = snapshotHelper.save(snapshot);

        // memberSnapshot에 update
        memberSnapshotRepository.updateSnapshotIdByMemberIdAndInsightId(saved.getId(), updatedBy, insightId);

        return insightDataMapper.insightToUpdateInsightResponse(insightUpdatedEvent.getInsight());
    }
}
