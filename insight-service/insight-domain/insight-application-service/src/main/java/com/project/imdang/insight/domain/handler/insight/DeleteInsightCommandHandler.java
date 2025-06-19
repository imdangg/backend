package com.project.imdang.insight.domain.handler.insight;

import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.InsightDomainService;
import com.project.imdang.insight.domain.dto.insight.delete.DeleteInsightCommand;
import com.project.imdang.insight.domain.entity.Insight;
import com.project.imdang.insight.domain.event.InsightDeletedEvent;
import com.project.imdang.insight.domain.helper.InsightHelper;
import com.project.imdang.insight.domain.ports.output.publisher.InsightDeletedEventMessagePublisher;
import com.project.imdang.insight.messaging.message.InsightDeletedEventMessage;
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

    private final InsightDeletedEventMessagePublisher insightDeletedEventMessagePublisher;

    @Transactional
    public InsightId deleteInsight(DeleteInsightCommand deleteInsightCommand) {
        InsightId insightId = deleteInsightCommand.getInsightId();
        Insight insight = insightHelper.get(insightId);

        // validation
        MemberId deletedBy = deleteInsightCommand.getMemberId();
        InsightDeletedEvent insightDeletedEvent = insightDomainService.deleteInsight(insight, deletedBy);
        Insight deletedInsight = insightHelper.save(insightDeletedEvent.getInsight());

        final InsightId deletedInsightId = deletedInsight.getId();
        log.info("Insight[id: {}] is deleted.", deletedInsightId.getValue());

        // publish
        InsightDeletedEventMessage insightDeletedEventMessage
                = new InsightDeletedEventMessage(deletedInsightId.getValue(), deletedInsight.getMemberId().getValue());
        insightDeletedEventMessagePublisher.publish(insightDeletedEventMessage);

        return deletedInsightId;
    }
}
