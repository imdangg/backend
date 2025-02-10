package com.project.imdang.insight.service.persistence.repository;

import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.persistence.entity.InsightEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface InsightJpaRepository extends JpaRepository<InsightEntity, UUID>, JpaSpecificationExecutor<InsightEntity> {

    Page<InsightEntity> findAll(Specification specification, Pageable pageable);
    Page<InsightEntity> findAllByCreatedAtBetween(ZonedDateTime start, ZonedDateTime end, Pageable pageable);
    List<InsightEntity> findAllByIdIn(Set<UUID> ids);

    @Query("select distinct i.apartmentComplex from InsightEntity i where i.memberId = :memberId")
    List<ApartmentComplex> findDistinctApartmentComplexByMemberId(UUID memberId);
}
