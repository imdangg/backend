package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.InsightDomainService;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightResponse;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.event.InsightDeletedEvent;
import com.project.imdang.insight.service.domain.exception.InsightNotFoundException;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.service.domain.ports.output.repository.InsightRepository;
import com.project.imdang.insight.service.domain.ports.output.repository.MemberSnapshotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class DeleteInsightCommandHandler {
    private final InsightDomainService insightDomainService;
    private final InsightRepository insightRepository;
    private final MemberSnapshotRepository memberSnapshotRepository;
    private final InsightDataMapper insightDataMapper;

    @Transactional
    public DeleteInsightResponse deleteInsight(DeleteInsightCommand deleteInsightCommand) {
        InsightId insightId = new InsightId(deleteInsightCommand.getInsightId());
        Insight insight = checkInsight(insightId);
        // validation
        MemberId deletedBy = new MemberId(deleteInsightCommand.getMemberId());

        // memberSnapshot에서 delete
        memberSnapshotRepository.deleteByMemberIdAndInsightId(deletedBy, insightId);

        InsightDeletedEvent insightDeletedEvent = insightDomainService.deleteInsight(insight, deletedBy);
        deleteInsight(insightDeletedEvent.getInsight().getId());
        log.info("Insight[id: {}] is deleted.", insightDeletedEvent.getInsight().getId().getValue());
        // TODO - publish event
        return insightDataMapper.insightToDeleteInsightResponse(insightDeletedEvent.getInsight());
    }

    private Insight checkInsight(InsightId insightId) {
        Optional<Insight> insightResult = insightRepository.findById(insightId);
        if (insightResult.isEmpty()) {
            throw new InsightNotFoundException(insightId);
        }
        return insightResult.get();
    }

    private void deleteInsight(InsightId insightId) {
        insightRepository.deleteById(insightId);
    }
}
