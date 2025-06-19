package com.project.imdang.insight.domain.handler.insight;

import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.InsightDomainService;
import com.project.imdang.insight.domain.dto.insight.accuse.AccuseInsightCommand;
import com.project.imdang.insight.domain.entity.Accuse;
import com.project.imdang.insight.domain.entity.Insight;
import com.project.imdang.insight.domain.event.InsightAccusedEvent;
import com.project.imdang.insight.domain.exception.AccuseAlreadyExistException;
import com.project.imdang.insight.domain.helper.AccuseHelper;
import com.project.imdang.insight.domain.helper.InsightHelper;
import com.project.imdang.insight.domain.ports.output.publisher.InsightAccusedEventMessagePublisher;
import com.project.imdang.insight.messaging.message.InsightAccusedEventMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Component
public class AccuseInsightCommandHandler {

    private final InsightDomainService insightDomainService;
    private final InsightHelper insightHelper;
    private final AccuseHelper accuseHelper;
    private final InsightAccusedEventMessagePublisher insightAccusedEventMessagePublisher;

    @Transactional
    public InsightId accuseInsight(AccuseInsightCommand accuseInsightCommand) {

        MemberId accusedBy = accuseInsightCommand.getAccuseMemberId();
        InsightId accusedInsightId = accuseInsightCommand.getInsightId();

        // accusedInsightId - memberId(accusedBy)로 중복 신고 여부 체크
        checkAlreadyAccused(accusedBy, accusedInsightId);

        Insight accusedInsight = insightHelper.get(accusedInsightId);
        InsightAccusedEvent insightAccusedEvent = insightDomainService.accuseInsight(accusedInsight, accusedBy);

        Insight savedInsight = insightHelper.save(insightAccusedEvent.getInsight());
        Accuse savedAccuse = accuseHelper.save(insightAccusedEvent.getAccuse());

        // publish
        InsightAccusedEventMessage insightAccusedEventMessage
                = new InsightAccusedEventMessage(accusedInsight.getId().getValue(), accusedInsight.getMemberId().getValue());
        insightAccusedEventMessagePublisher.publish(insightAccusedEventMessage);

        log.info("Insight[id: {}] is accused by Member[id: {}].", savedInsight.getId().getValue(), savedAccuse.getAccuseMemberId().getValue());
        return savedInsight.getId();
    }

    private void checkAlreadyAccused(MemberId accusedBy, InsightId accusedInsightId) {
        Optional<Accuse> optional = accuseHelper.getByAccuseMemberIdAndAccusedInsightId(accusedBy, accusedInsightId);
        if (optional.isPresent()) {
            throw new AccuseAlreadyExistException();
        }
    }
}
