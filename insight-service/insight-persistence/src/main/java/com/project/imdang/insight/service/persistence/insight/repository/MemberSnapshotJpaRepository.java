package com.project.imdang.insight.service.persistence.insight.repository;

import com.project.imdang.insight.service.persistence.insight.entity.MemberSnapshotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberSnapshotJpaRepository extends JpaRepository<MemberSnapshotEntity, Long> {
}
