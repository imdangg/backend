package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.InsightDomainService;
import com.project.imdang.insight.service.domain.dto.insight.accuse.AccuseInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.accuse.AccuseInsightResponse;
import com.project.imdang.insight.service.domain.entity.Accuse;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.event.InsightAccusedEvent;
import com.project.imdang.insight.service.domain.exception.InsightApplicationServiceException;
import com.project.imdang.insight.service.domain.handler.AccuseHelper;
import com.project.imdang.insight.service.domain.handler.InsightHelper;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.project.imdang.domain.exception.ErrorCode.ALREADY_ACCUSED;


@Slf4j
@RequiredArgsConstructor
@Component
public class AccuseInsightCommandHandler {
// TODO - 배치
// 상대방의 신고 횟수 + 1
    private final InsightDomainService insightDomainService;
    private final InsightHelper insightHelper;
    private final InsightDataMapper insightDataMapper;

    private final AccuseHelper accuseHelper;

    @Transactional
    public AccuseInsightResponse accuseInsight(AccuseInsightCommand accuseInsightCommand) {

        MemberId accusedBy = new MemberId(accuseInsightCommand.getAccuseMemberId());
        InsightId accusedInsightId = new InsightId(accuseInsightCommand.getInsightId());

        // accusedInsightId - memberId(accusedBy)로 중복 신고 여부 체크
        checkAlreadyAccused(accusedBy, accusedInsightId);

        Insight accusedInsight = insightHelper.get(accusedInsightId);
        InsightAccusedEvent insightAccusedEvent = insightDomainService.accuseInsight(accusedInsight, accusedBy);
        Insight savedInsight = insightHelper.save(insightAccusedEvent.getInsight());
        // TODO - publish event
        Accuse savedAccuse = accuseHelper.save(insightAccusedEvent.getAccuse());

        log.info("Insight[id: {}] is accused by Member[id: {}].", savedInsight.getId().getValue(), savedAccuse.getAccuseMemberId().getValue());
        return insightDataMapper.insightToAccuseInsightResponse(savedInsight);
    }

    private void checkAlreadyAccused(MemberId accusedBy, InsightId accusedInsightId) {
        Optional<Accuse> optional = accuseHelper.getByAccuseMemberIdAndAccusedInsightId(accusedBy, accusedInsightId);
        if (optional.isPresent()) {
            throw new InsightApplicationServiceException(ALREADY_ACCUSED);
        }
    }
}
