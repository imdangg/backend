package com.project.imdang.member.persistence.adapter;

import com.project.imdang.member.domain.entity.ActualLiving;
import com.project.imdang.member.domain.entity.GapInvestment;
import com.project.imdang.member.domain.ports.output.repository.ConditionRepository;
import com.project.imdang.member.persistence.entity.ActualLivingEntity;
import com.project.imdang.member.persistence.entity.GapInvestmentEntity;
import com.project.imdang.member.persistence.mapper.ActualLivingPersistenceMapper;
import com.project.imdang.member.persistence.mapper.GapInvestmentPersistenceMapper;
import com.project.imdang.member.persistence.repository.ActualLivingJpaRepository;
import com.project.imdang.member.persistence.repository.GapInvestmentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ConditionRepositoryImpl implements ConditionRepository {

    private final GapInvestmentJpaRepository gapInvestmentJpaRepository;
    private final GapInvestmentPersistenceMapper gapInvestmentMapper;
    private final ActualLivingJpaRepository actualLivingJpaRepository;
    private final ActualLivingPersistenceMapper actualLivingMapper;

    @Override
    public GapInvestment saveGapInvestment(GapInvestment gapInvestment) {
        GapInvestmentEntity gapInvestmentEntity = gapInvestmentMapper.gapInvestmentToGapInvestmentEntity(gapInvestment);
        GapInvestmentEntity saved = gapInvestmentJpaRepository.save(gapInvestmentEntity);
        return gapInvestmentMapper.gapInvestmentEntityToGapInvestment(saved);
    }

    @Override
    public ActualLiving saveActualLiving(ActualLiving actualLiving) {
        ActualLivingEntity actualLivingEntity = actualLivingMapper.actualLivingToActualLivingEntity(actualLiving);
        ActualLivingEntity saved = actualLivingJpaRepository.save(actualLivingEntity);
        return actualLivingMapper.actualLivingEntityToActualLiving(saved);
    }
}
