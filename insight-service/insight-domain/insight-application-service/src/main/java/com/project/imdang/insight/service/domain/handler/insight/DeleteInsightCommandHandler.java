package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.InsightDomainService;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightResponse;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.event.InsightDeletedEvent;
import com.project.imdang.insight.service.domain.handler.InsightHelper;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class DeleteInsightCommandHandler {
    private final InsightDomainService insightDomainService;
    private final InsightHelper insightHelper;
    private final InsightDataMapper insightDataMapper;

    @Transactional
    public DeleteInsightResponse deleteInsight(DeleteInsightCommand deleteInsightCommand) {
        InsightId insightId = new InsightId(deleteInsightCommand.getInsightId());
        Insight insight = insightHelper.get(insightId);

        // validation
        MemberId deletedBy = new MemberId(deleteInsightCommand.getMemberId());
        InsightDeletedEvent insightDeletedEvent = insightDomainService.deleteInsight(insight, deletedBy);
        insightHelper.save(insightDeletedEvent.getInsight());
        log.info("Insight[id: {}] is deleted.", insightDeletedEvent.getInsight().getId().getValue());
        // TODO - publish event
        return insightDataMapper.insightToDeleteInsightResponse(insightDeletedEvent.getInsight());
    }
}
