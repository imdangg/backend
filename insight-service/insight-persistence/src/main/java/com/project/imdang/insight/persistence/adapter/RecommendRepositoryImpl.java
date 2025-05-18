package com.project.imdang.insight.persistence.adapter;

import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.entity.Recommend;
import com.project.imdang.insight.domain.ports.output.repository.RecommendRepository;
import com.project.imdang.insight.persistence.repository.RecommendJpaRepository;
import com.project.imdang.insight.persistence.entity.RecommendEntity;
import com.project.imdang.insight.persistence.mapper.RecommendPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class RecommendRepositoryImpl implements RecommendRepository {

    private final RecommendJpaRepository recommendJpaRepository;
    private final RecommendPersistenceMapper recommendPersistenceMapper;

    @Override
    public Optional<Recommend> findByRecommendMemberIdAndRecommendedInsightId(MemberId recommendMemberId, InsightId recommendedInsightId) {
        return recommendJpaRepository.findByRecommendMemberIdAndRecommendedInsightId(recommendMemberId.getValue(), recommendedInsightId.getValue())
                .map(recommendPersistenceMapper::recommendEntityToRecommend);
    }

    @Override
    public Recommend save(Recommend recommend) {
        RecommendEntity recommendEntity = recommendPersistenceMapper.recommendToRecommendEntity(recommend);
        RecommendEntity saved = recommendJpaRepository.save(recommendEntity);
        return recommendPersistenceMapper.recommendEntityToRecommend(saved);
    }
}
