package com.project.imdang.insight.domain.handler.insight;

import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.InsightDomainService;
import com.project.imdang.insight.domain.dto.insight.recommend.RecommendInsightCommand;
import com.project.imdang.insight.domain.entity.Insight;
import com.project.imdang.insight.domain.entity.Recommend;
import com.project.imdang.insight.domain.event.InsightRecommendedEvent;
import com.project.imdang.insight.domain.event.InsightUnRecommendedEvent;
import com.project.imdang.insight.domain.helper.InsightHelper;
import com.project.imdang.insight.domain.helper.RecommendHelper;
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

    private final RecommendHelper recommendHelper;

    @Transactional
    public InsightId recommendInsight(RecommendInsightCommand recommendInsightCommand) {

        MemberId recommendedBy = recommendInsightCommand.getRecommendMemberId();
        InsightId recommendedInsightId = recommendInsightCommand.getInsightId();
        Optional<Recommend> optionalRecommend = recommendHelper.getByRecommendMemberIdAndRecommendedInsightId(recommendedBy, recommendedInsightId);

        Insight recommendedInsight = insightHelper.get(recommendedInsightId);
        Insight savedInsight;

        //이미 추천 한 경우
        if (optionalRecommend.isPresent()) {
            //인사이트 추천수 감소
            InsightUnRecommendedEvent insightUnRecommendedEvent = insightDomainService.unRecommendInsight(recommendedInsight, recommendedBy);
            savedInsight = insightHelper.save(insightUnRecommendedEvent.getInsight());
            //기존 추천 지우기
            recommendHelper.delete(optionalRecommend.get());
            log.info("Insight[id: {}] is unrecommended by Member[id: {}].", savedInsight.getId().getValue(), recommendedBy.getValue());
        }
        else {
            //인사이트 추천수 증가
            InsightRecommendedEvent insightRecommendedEvent = insightDomainService.recommendInsight(recommendedInsight, recommendedBy);
            savedInsight = insightHelper.save(insightRecommendedEvent.getInsight());
            //추천 추가
            Recommend savedRecommend = recommendHelper.save(insightRecommendedEvent.getRecommend());
            log.info("Insight[id: {}] is recommended by Member[id: {}].", savedInsight.getId().getValue(), savedRecommend.getRecommendMemberId().getValue());
        }
        return savedInsight.getId();
    }
}
