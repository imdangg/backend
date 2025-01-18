package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.insight.service.domain.InsightDomainService;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightResponse;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.handler.InsightHelper;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class RecommendInsightCommandHandler {
    // 교환 완료해야 상대방 인사이트 추천 가능
    // 교환하지 않은 상태에서 추천 클릭 시 "인사이트 교환해야 추천할 수 있어요" alert

    private final InsightDomainService insightDomainService;
    private final InsightHelper insightHelper;
    private final InsightDataMapper insightDataMapper;

    @Transactional
    public RecommendInsightResponse recommendInsight(RecommendInsightCommand recommendInsightCommand) {
        InsightId insightId = new InsightId(recommendInsightCommand.getInsightId());
        Insight insight = insightHelper.get(insightId);

        // TODO - CHECK : insightId - memberId(recommendedBy)로 중복 추천 여부 체크


        Insight recommended = insightDomainService.recommendInsight(insight);
        insightHelper.save(recommended);
        log.info("Insight[id: {}] is recommended.", recommended.getId().getValue());
        return insightDataMapper.insightToRecommendInsightResponse(recommended);
    }
}
