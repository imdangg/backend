package com.project.imdang.insight.service.persistence.insight.adapter;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.Accuse;
import com.project.imdang.insight.service.domain.ports.output.repository.AccuseRepository;
import com.project.imdang.insight.service.persistence.insight.entity.AccuseEntity;
import com.project.imdang.insight.service.persistence.insight.mapper.AccusePersistenceMapper;
import com.project.imdang.insight.service.persistence.insight.repository.AccuseJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AccuseRepositoryImpl implements AccuseRepository {

    private final AccuseJpaRepository accuseJpaRepository;
    private final AccusePersistenceMapper accusePersistenceMapper;

    @Override
    public Optional<Accuse> findByAccuseMemberIdAndAccusedInsightId(MemberId accuseMemberId, InsightId accusedInsightId) {
        return accuseJpaRepository.findByAccuseMemberIdAndAccusedInsightId(accuseMemberId.getValue(), accusedInsightId.getValue())
                .map(accusePersistenceMapper::accuseEntityToAccuse);
    }

    @Override
    public Accuse save(Accuse accuse) {
        AccuseEntity accuseEntity = accusePersistenceMapper.accuseToAccuseEntity(accuse);
        AccuseEntity saved = accuseJpaRepository.save(accuseEntity);
        return accusePersistenceMapper.accuseEntityToAccuse(saved);
    }
}
