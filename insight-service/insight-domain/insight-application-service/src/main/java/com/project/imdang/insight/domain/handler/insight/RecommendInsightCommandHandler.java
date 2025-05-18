package com.project.imdang.insight.domain.handler.insight;

import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.InsightDomainService;
import com.project.imdang.insight.domain.dto.insight.recommend.RecommendInsightCommand;
import com.project.imdang.insight.domain.entity.Insight;
import com.project.imdang.insight.domain.entity.Recommend;
import com.project.imdang.insight.domain.event.InsightRecommendedEvent;
import com.project.imdang.insight.domain.exception.RecommendAlreadyExistException;
import com.project.imdang.insight.domain.helper.InsightHelper;
import com.project.imdang.insight.domain.helper.RecommendHelper;
import com.project.imdang.insight.domain.mapper.InsightDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class RecommendInsightCommandHandler {

    private final InsightDomainService insightDomainService;
    private final InsightHelper insightHelper;
    private final InsightDataMapper insightDataMapper;

    private final RecommendHelper recommendHelper;

    @Transactional
    public InsightId recommendInsight(RecommendInsightCommand recommendInsightCommand) {

        MemberId recommendedBy = recommendInsightCommand.getRecommendMemberId();
        InsightId recommendedInsightId = recommendInsightCommand.getInsightId();
        // recommendedInsightId - memberId(recommendedBy)로 중복 추천 여부 체크
        checkAlreadyRecommended(recommendedBy, recommendedInsightId);

        Insight recommendedInsight = insightHelper.get(recommendedInsightId);
        InsightRecommendedEvent insightRecommendedEvent = insightDomainService.recommendInsight(recommendedInsight, recommendedBy);
        Insight savedInsight = insightHelper.save(insightRecommendedEvent.getInsight());

        Recommend savedRecommend = recommendHelper.save(insightRecommendedEvent.getRecommend());
        log.info("Insight[id: {}] is recommended by Member[id: {}].", savedInsight.getId().getValue(), savedRecommend.getRecommendMemberId().getValue());
        return savedInsight.getId();
    }

    private void checkAlreadyRecommended(MemberId recommendedBy, InsightId recommendedInsightId) {
        Optional<Recommend> optional = recommendHelper.getByRecommendMemberIdAndRecommendedInsightId(recommendedBy, recommendedInsightId);
        if (optional.isPresent()) {
            throw new RecommendAlreadyExistException();
        }
    }
}
