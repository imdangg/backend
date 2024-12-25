package com.project.imdang.insight.service.persistence.insight.adapter;

import com.project.imdang.domain.valueobject.BaseId;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.ports.output.repository.InsightRepository;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.persistence.insight.entity.InsightEntity;
import com.project.imdang.insight.service.persistence.insight.mapper.InsightPersistenceMapper;
import com.project.imdang.insight.service.persistence.insight.repository.InsightJpaRepository;
import com.project.imdang.insight.service.persistence.insight.repository.InsightSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class InsightRepositoryImpl implements InsightRepository {

    private final InsightJpaRepository insightJpaRepository;
    private final InsightPersistenceMapper insightPersistenceMapper;

    @Override
    public Page<Insight> findAll(PageRequest pageRequest) {
        return insightJpaRepository.findAll(pageRequest)
                .map(insightPersistenceMapper::insightEntityToInsight);
    }
/*
    @Override
    public Page<Insight> findAllByIds(Set<InsightId> insightIds, PagingRequest pagingRequest) {
        PageRequest pageRequest = PageRequest.of(pagingRequest.getPageNumber(), pagingRequest.getPageSize(), Sort.Direction.valueOf(pagingRequest.getDirection()), pagingRequest.getProperties());
        Set<UUID> _insightIds = insightIds.stream()
                .map(BaseId::getValue)
                .collect(Collectors.toSet());
        Page<InsightEntity> pagedInsightEntities = insightJpaRepository.findAllByIdIn(_insightIds, pageRequest);
        List<Insight> insights = pagedInsightEntities.getContent().stream()
                .map(insightPersistenceMapper::insightEntityToInsight)
                .toList();
        return Paged.<Insight>builder()
                .contents(insights)
                .number(pagedInsightEntities.getNumber())
                .size(pagedInsightEntities.getSize())
                .numberOfElements(pagedInsightEntities.getNumberOfElements())
                .totalPages(pagedInsightEntities.getTotalPages())
                .totalElements(pagedInsightEntities.getTotalElements())
                .build();
    }*/

    @Override
    public List<Insight> findAllByIds(List<InsightId> insightIds) {
        Set<UUID> _insightIds = insightIds.stream()
                .map(BaseId::getValue)
                .collect(Collectors.toSet());
        return insightJpaRepository.findAllByIdIn(_insightIds).stream()
                .map(insightPersistenceMapper::insightEntityToInsight)
                .toList();
    }

    @Override
    public Page<Insight> findAllByApartmentComplex(ApartmentComplex apartmentComplex, PageRequest pageRequest) {
        Specification<InsightEntity> specification = Specification.where(InsightSpecification.equalsApartmentComplex(apartmentComplex.getName()));
        return insightJpaRepository.findAll(specification, pageRequest)
                .map(insightPersistenceMapper::insightEntityToInsight);
    }

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

    @Override
    public List<ApartmentComplex> findDistinctApartmentComplexByMemberId(MemberId memberId) {
        return insightJpaRepository.findDistinctApartmentComplexByMemberId(memberId.getValue());
    }
}
