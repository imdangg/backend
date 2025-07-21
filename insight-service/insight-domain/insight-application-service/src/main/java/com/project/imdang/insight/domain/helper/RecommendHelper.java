package com.project.imdang.insight.domain.helper;

import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.entity.Recommend;
import com.project.imdang.insight.domain.exception.InsightDomainException;
import com.project.imdang.insight.domain.ports.output.repository.RecommendRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class RecommendHelper {

    private final RecommendRepository recommendRepository;

    public Recommend save(Recommend recommend) {
        Recommend saved = recommendRepository.save(recommend);
        if (saved == null) {
            String errorMessage = "Could not save recommend!";
            log.error(errorMessage);
            throw new InsightDomainException(errorMessage);
        }
        log.info("Recommend[id: {}] is saved.", saved.getId().getValue());
        return saved;
    }

    public void delete(Recommend recommend) {
        recommendRepository.delete(recommend);
        log.info("Recommend[id: {}] is deleted.", recommend.getId().getValue());
    }

    public Optional<Recommend> getByRecommendMemberIdAndRecommendedInsightId(MemberId recommendMemberId, InsightId recommendedInsightId) {
        return recommendRepository.findByRecommendMemberIdAndRecommendedInsightId(recommendMemberId, recommendedInsightId);
    }
}
