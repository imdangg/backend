package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.InsightDomainService;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightResponse;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.entity.Recommend;
import com.project.imdang.insight.service.domain.event.InsightRecommendedEvent;
import com.project.imdang.insight.service.domain.exception.InsightApplicationServiceException;
import com.project.imdang.insight.service.domain.handler.InsightHelper;
import com.project.imdang.insight.service.domain.handler.MemberSnapshotHelper;
import com.project.imdang.insight.service.domain.handler.RecommendHelper;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.project.imdang.domain.exception.ErrorCode.ALREADY_RECOMMENDED;
import static com.project.imdang.domain.exception.ErrorCode.EXCHANGE_REQUIRED;

@Slf4j
@RequiredArgsConstructor
@Component
public class RecommendInsightCommandHandler {

    private final InsightDomainService insightDomainService;
    private final InsightHelper insightHelper;
    private final InsightDataMapper insightDataMapper;

    private final RecommendHelper recommendHelper;
    private final MemberSnapshotHelper memberSnapshotHelper;

    @Transactional
    public RecommendInsightResponse recommendInsight(RecommendInsightCommand recommendInsightCommand) {

        MemberId recommendedBy = new MemberId(recommendInsightCommand.getMemberId());
        InsightId recommendedInsightId = new InsightId(recommendInsightCommand.getInsightId());

        // 교환 완료해야 상대방 인사이트 추천 가능
        // 교환하지 않은 상태에서 추천 클릭 시 "인사이트 교환해야 추천할 수 있어요" alert
        // member_snapshot 확인
        checkExchangeCompleted(recommendedBy, recommendedInsightId);

        // recommendedInsightId - memberId(recommendedBy)로 중복 추천 여부 체크
        checkAlreadyRecommended(recommendedBy, recommendedInsightId);

        Insight recommendedInsight = insightHelper.get(recommendedInsightId);
        InsightRecommendedEvent insightRecommendedEvent = insightDomainService.recommendInsight(recommendedInsight, recommendedBy);
        Insight savedInsight = insightHelper.save(insightRecommendedEvent.getInsight());

        Recommend savedRecommend = recommendHelper.save(insightRecommendedEvent.getRecommend());
        log.info("Insight[id: {}] is recommended by Member[id: {}].", savedInsight.getId().getValue(), savedRecommend.getRecommendMemberId().getValue());
        return insightDataMapper.insightToRecommendInsightResponse(savedInsight);
    }

    private void checkExchangeCompleted(MemberId recommendedBy, InsightId insightId) {
        memberSnapshotHelper.getByMemberIdAndInsightId(recommendedBy, insightId)
                .orElseThrow(() -> new InsightApplicationServiceException(EXCHANGE_REQUIRED));
    }

    private void checkAlreadyRecommended(MemberId recommendedBy, InsightId recommendedInsightId) {
        Optional<Recommend> optional = recommendHelper.getByRecommendMemberIdAndRecommendedInsightId(recommendedBy, recommendedInsightId);
        if (optional.isPresent()) {
            throw new InsightApplicationServiceException(ALREADY_RECOMMENDED);
        }
    }
}
