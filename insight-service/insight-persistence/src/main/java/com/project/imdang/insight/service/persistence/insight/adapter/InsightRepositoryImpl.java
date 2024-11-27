package com.project.imdang.insight.service.persistence.insight.adapter;

import com.project.imdang.domain.valueobject.ExchangeRequestId;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.ports.output.repository.InsightRepository;
import com.project.imdang.insight.service.persistence.insight.entity.ExchangeRequestEntity;
import com.project.imdang.insight.service.persistence.insight.mapper.InsightPersistenceMapper;
import com.project.imdang.insight.service.persistence.insight.repository.InsightJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class InsightRepositoryImpl implements InsightRepository {

    private final InsightJpaRepository insightJpaRepository;
    private final InsightPersistenceMapper insightPersistenceMapper;

    @Override
    public Optional<Insight> findInsight(InsightId insightId) {
        return Optional.empty();
    }

    @Override
    public Insight save(Insight insight) {
        return null;
    }

    @Override
    public void deleteInsight(UUID insightId) {

    }
}
