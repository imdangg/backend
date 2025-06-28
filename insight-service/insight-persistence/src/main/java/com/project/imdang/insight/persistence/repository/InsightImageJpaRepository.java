package com.project.imdang.insight.persistence.repository;

import com.project.imdang.common.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.persistence.entity.InsightEntity;
import com.project.imdang.insight.persistence.entity.InsightImageEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface InsightImageJpaRepository extends JpaRepository<InsightImageEntity, UUID>, JpaSpecificationExecutor<InsightImageEntity> {

    List<InsightImageEntity> findByInsightId(UUID insightId);
    List<InsightImageEntity> findAllByInsightIdIn(Set<UUID> insightIds);

    @Modifying
    @Query("DELETE FROM InsightImageEntity i WHERE i.insightId = :insightId")
    void deleteByInsightId(@Param("insightId") UUID insightId);
}
