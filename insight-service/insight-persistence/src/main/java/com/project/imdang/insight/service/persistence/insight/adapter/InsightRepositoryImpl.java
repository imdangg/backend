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
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class InsightRepositoryImpl implements InsightRepository {

    private final InsightJpaRepository insightJpaRepository;
    private final InsightPersistenceMapper insightPersistenceMapper;

    @Override
    public Optional<Insight> findById(InsightId insightId) {
        UUID _insightId = insightId.getValue();
        return insightJpaRepository.findById(_insightId)
                .map(insightPersistenceMapper::insightEntityToInsight);
    }

    @Override
    public Insight save(Insight insight) {
        InsightEntity insightEntity = insightPersistenceMapper.insightToInsightEntity(insight);
        InsightEntity saved = insightJpaRepository.save(insightEntity);
        return insightPersistenceMapper.insightEntityToInsight(saved);
    }

    @Override
    public void deleteById(InsightId insightId) {
        UUID _insightId = insightId.getValue();
        insightJpaRepository.deleteById(_insightId);
    }
}
