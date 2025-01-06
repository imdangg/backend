package com.project.imdang.insight.service.domain.handler.insight;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.insight.service.domain.InsightDomainService;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightResponse;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.exception.InsightDomainException;
import com.project.imdang.insight.service.domain.exception.InsightNotFoundException;
import com.project.imdang.insight.service.domain.mapper.InsightDataMapper;
import com.project.imdang.insight.service.domain.ports.output.repository.InsightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class RecommendInsightCommandHandler {
    // 교환 완료해야 상대방 인사이트 추천 가능
    // 교환하지 않은 상태에서 추천 클릭 시 "인사이트 교환해야 추천할 수 있어요" alert

    private final InsightDomainService insightDomainService;
    private final InsightRepository insightRepository;
    private final InsightDataMapper insightDataMapper;

    @Transactional
    public RecommendInsightResponse recommendInsight(RecommendInsightCommand recommendInsightCommand) {
        UUID insightId = recommendInsightCommand.getInsightId();
        Insight insight = checkInsight(insightId);

        // TODO - CHECK : insightId - memberId(recommendedBy)로 중복 추천 여부 체크

        Insight recommended = insightDomainService.recommendInsight(insight);
        saveInsight(recommended);
        log.info("Insight[id: {}] is recommended.", recommended.getId().getValue());
        return insightDataMapper.insightToRecommendInsightResponse(recommended);
    }

    private Insight checkInsight(UUID _insightId) {
        InsightId insightId = new InsightId(_insightId);
        Optional<Insight> insightResult = insightRepository.findById(insightId);
        if (insightResult.isEmpty()) {
            throw new InsightNotFoundException(insightId);
        }
        return insightResult.get();
    }

    private Insight saveInsight(Insight insight) {
        Insight saved = insightRepository.save(insight);
        if (saved == null) {
            String errorMessage = "Could not save insight!";
            log.error(errorMessage);
            throw new InsightDomainException(errorMessage);
        }
        log.info("Insight[id: {}] is saved.", saved.getId().getValue());
        return saved;
    }
}
