package com.project.imdang.insight.service.persistence.insight.adapter;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.ports.output.repository.InsightRepository;
import com.project.imdang.insight.service.persistence.insight.entity.InsightEntity;
import com.project.imdang.insight.service.persistence.insight.mapper.InsightPersistenceMapper;
import com.project.imdang.insight.service.persistence.insight.repository.InsightJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class InsightRepositoryImpl implements InsightRepository {

    private final InsightJpaRepository insightJpaRepository;
    private final InsightPersistenceMapper insightPersistenceMapper;

    @Override
    public Optional<Insight> findInsight(InsightId insightId) {
        Optional<InsightEntity> insightEntity = insightJpaRepository.findById(insightId.getValue());
        return insightEntity.map(insightPersistenceMapper::insightEntityToInsight);
    }

    @Override
    public Insight save(Insight insight) {
        InsightEntity insightEntity = insightPersistenceMapper.insightToInsightEntity(insight);
        InsightEntity saved = insightJpaRepository.save(insightEntity);
        return insightPersistenceMapper.insightEntityToInsight(saved);
    }

    @Override
    public void deleteInsight(InsightId insightId) {
        insightJpaRepository.deleteById(insightId.getValue());
    }
}
