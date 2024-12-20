package com.project.imdang.insight.service.persistence.insight.repository;

import com.project.imdang.insight.service.persistence.insight.entity.MemberSnapshotEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MemberSnapshotJpaRepository extends JpaRepository<MemberSnapshotEntity, Long> {
    Page<MemberSnapshotEntity> findAllByMemberId(UUID memberId, Pageable pageable);
}
