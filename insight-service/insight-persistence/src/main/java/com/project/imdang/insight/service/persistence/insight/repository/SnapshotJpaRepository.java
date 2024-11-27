package com.project.imdang.insight.service.persistence.insight.repository;

import com.project.imdang.insight.service.persistence.insight.entity.SnapshotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnapshotJpaRepository extends JpaRepository<SnapshotEntity, Long> {
}
