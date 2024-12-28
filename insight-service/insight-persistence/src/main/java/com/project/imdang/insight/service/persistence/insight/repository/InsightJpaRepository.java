package com.project.imdang.insight.service.persistence.insight.repository;

import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.persistence.insight.entity.InsightEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface InsightJpaRepository extends JpaRepository<InsightEntity, UUID>, JpaSpecificationExecutor<InsightEntity> {

    Page<InsightEntity> findAll(Specification specification, Pageable pageable);
    List<InsightEntity> findAllByIdIn(Set<UUID> ids);

    @Query("select distinct i.apartmentComplex from InsightEntity i")
    List<ApartmentComplex> findDistinctApartmentComplexByMemberId(UUID memberId);
}
