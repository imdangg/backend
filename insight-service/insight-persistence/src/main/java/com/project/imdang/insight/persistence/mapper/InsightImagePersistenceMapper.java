package com.project.imdang.insight.persistence.mapper;

import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.entity.Insight;
import com.project.imdang.insight.domain.entity.InsightImage;
import com.project.imdang.insight.persistence.entity.InsightEntity;
import com.project.imdang.insight.persistence.entity.InsightImageEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InsightImagePersistenceMapper {

    public InsightImageEntity toEntity(InsightImage image) {
        return InsightImageEntity.builder()
                .image(image.getImage())
                .insightId(image.getInsightId().getValue())
                .type(image.getType())
                .sortNum(image.getSortNum())
                .createdAt(image.getCreatedAt())
                .build();
    }

    public List<InsightImageEntity> toEntities(List<InsightImage> images) {
        return images.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public InsightImage toDomain(InsightImageEntity entity) {
        return InsightImage.builder()
                .image(entity.getImage())
                .insightId(new InsightId(entity.getInsightId()))
                .type(entity.getType())
                .sortNum(entity.getSortNum())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public List<InsightImage> toDomains(List<InsightImageEntity> entities) {
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}
