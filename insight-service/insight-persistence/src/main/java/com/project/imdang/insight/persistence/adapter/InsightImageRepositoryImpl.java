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
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Override
    public List<InsightImage> findByInsightId(InsightId insightId) {
        List<InsightImageEntity> entities = insightImageJpaRepository.findByInsightId(insightId.getValue());
        return insightImagePersistenceMapper.toDomains(entities);
    }

    @Override
    public List<InsightImage> findByInsightIdIn(List<InsightId> insightIds) {
        Set<UUID> insightUuidSet = insightIds.stream()
                .map(BaseId::getValue)
                .collect(Collectors.toSet());

        List<InsightImageEntity> entities = insightImageJpaRepository.findAllByInsightIdIn(insightUuidSet);
        return insightImagePersistenceMapper.toDomains(entities);
    }

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

    @Override
    public void deleteByInsightId(UUID insightId) {
        insightImageJpaRepository.deleteByInsightId(insightId);
    }
}
