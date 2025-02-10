package com.project.imdang.insight.service.persistence.adapter;

import com.project.imdang.domain.valueobject.BaseId;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.ports.output.repository.InsightRepository;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.domain.valueobject.District;
import com.project.imdang.insight.service.persistence.entity.InsightEntity;
import com.project.imdang.insight.service.persistence.mapper.InsightPersistenceMapper;
import com.project.imdang.insight.service.persistence.repository.InsightJpaRepository;
import com.project.imdang.insight.service.persistence.repository.InsightSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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

    @Override
    public Page<Insight> findAllByDate(LocalDate date, PageRequest pageRequest) {
        final ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime startOfDay = date.atStartOfDay(zoneId);
        ZonedDateTime endOfDay = date.atTime(LocalTime.MAX).atZone(zoneId);
        return insightJpaRepository.findAllByCreatedAtBetween(startOfDay, endOfDay, pageRequest)
                .map(insightPersistenceMapper::insightEntityToInsight);
    }

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
    public Page<Insight> findAllByDistrict(District district, PageRequest pageRequest) {
        Specification<InsightEntity> specification =
                Specification.where(InsightSpecification.equalsSiDo(district.getSiDo()))
                        .and(InsightSpecification.equalsSiGunGu(district.getSiGunGu()))
                        .and(InsightSpecification.equalsEupMyeonDong(district.getEupMyeonDong()));
        return insightJpaRepository.findAll(specification, pageRequest)
                .map(insightPersistenceMapper::insightEntityToInsight);
    }

    @Override
    public Page<Insight> findAllByApartmentComplex(ApartmentComplex apartmentComplex, PageRequest pageRequest) {
        Specification<InsightEntity> specification = Specification.where(InsightSpecification.equalsApartmentComplexName(apartmentComplex.getName()));
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
