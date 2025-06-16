package com.project.imdang.insight.persistence.adapter;

import com.project.imdang.common.domain.valueobject.*;
import com.project.imdang.insight.domain.entity.Insight;
import com.project.imdang.insight.domain.entity.InsightImage;
import com.project.imdang.insight.domain.ports.output.repository.InsightImageRepository;
import com.project.imdang.insight.domain.ports.output.repository.InsightRepository;
import com.project.imdang.insight.persistence.entity.InsightEntity;
import com.project.imdang.insight.persistence.entity.InsightImageEntity;
import com.project.imdang.insight.persistence.mapper.InsightImagePersistenceMapper;
import com.project.imdang.insight.persistence.mapper.InsightPersistenceMapper;
import com.project.imdang.insight.persistence.repository.InsightImageJpaRepository;
import com.project.imdang.insight.persistence.repository.InsightJpaRepository;
import com.project.imdang.insight.persistence.repository.InsightSpecification;
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
public class InsightImageRepositoryImpl implements InsightImageRepository {

    private final InsightImageJpaRepository insightImageJpaRepository;
    private final InsightImagePersistenceMapper insightImagePersistenceMapper;

//    @Override
//    public Page<Insight> findAll(PageRequest pageRequest) {
//        return insightJpaRepository.findAll(pageRequest)
//                .map(insightPersistenceMapper::insightEntityToInsight);
//    }
//
//    @Override
//    public Page<Insight> findAllByDate(LocalDate date, PageRequest pageRequest) {
//        final ZoneId zoneId = ZoneId.systemDefault();
//        ZonedDateTime startOfDay = date.atStartOfDay(zoneId);
//        ZonedDateTime endOfDay = date.atTime(LocalTime.MAX).atZone(zoneId);
//        return insightJpaRepository.findAllByCreatedAtBetween(startOfDay, endOfDay, pageRequest)
//                .map(insightPersistenceMapper::insightEntityToInsight);
//    }
//
//    @Override
//    public List<Insight> findAllByIds(List<InsightId> insightIds) {
//        Set<UUID> _insightIds = insightIds.stream()
//                .map(BaseId::getValue)
//                .collect(Collectors.toSet());
//        return insightJpaRepository.findAllByIdIn(_insightIds).stream()
//                .map(insightPersistenceMapper::insightEntityToInsight)
//                .toList();
//    }
//
//    @Override
//    public Page<Insight> findAllByDistrict(District district, PageRequest pageRequest) {
//        Specification<InsightEntity> specification =
//                Specification.where(InsightSpecification.equalsSiDo(district.getSiDo()))
//                        .and(InsightSpecification.equalsSiGunGu(district.getSiGunGu()))
//                        .and(InsightSpecification.equalsEupMyeonDong(district.getEupMyeonDong()));
//        return insightJpaRepository.findAll(specification, pageRequest)
//                .map(insightPersistenceMapper::insightEntityToInsight);
//    }
//
//    @Override
//    public Page<Insight> findAllByApartmentComplex(ApartmentComplex apartmentComplex, PageRequest pageRequest) {
//        Specification<InsightEntity> specification = Specification.where(InsightSpecification.equalsApartmentComplexName(apartmentComplex.getName()));
//        return insightJpaRepository.findAll(specification, pageRequest)
//                .map(insightPersistenceMapper::insightEntityToInsight);
//    }
//
//    @Override
//    public Optional<Insight> findById(InsightId insightId) {
//        UUID _insightId = insightId.getValue();
//        return insightJpaRepository.findById(_insightId)
//                .map(insightPersistenceMapper::insightEntityToInsight);
//    }

    @Override
    public InsightImage save(InsightImage image) {
        InsightImageEntity insightImageEntity = insightImagePersistenceMapper.toEntity(image);
        InsightImageEntity saved = insightImageJpaRepository.save(insightImageEntity);
        return insightImagePersistenceMapper.toDomain(saved);
    }
    @Override
    public List<InsightImage> saveAll(List<InsightImage> images) {
        List<InsightImageEntity> entities = insightImagePersistenceMapper.toEntities(images);
        List<InsightImageEntity> saved = insightImageJpaRepository.saveAll(entities);
        return insightImagePersistenceMapper.toDomains(saved);
    }

//    @Override
//    public List<ApartmentComplex> findDistinctApartmentComplexByMemberId(MemberId memberId) {
//        return insightJpaRepository.findDistinctApartmentComplexByMemberId(memberId.getValue());
//    }
}
