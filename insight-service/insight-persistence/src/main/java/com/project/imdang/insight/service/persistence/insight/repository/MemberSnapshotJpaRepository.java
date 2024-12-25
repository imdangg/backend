package com.project.imdang.insight.service.persistence.insight.repository;

import com.project.imdang.insight.service.persistence.insight.entity.MemberSnapshotEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberSnapshotJpaRepository extends JpaRepository<MemberSnapshotEntity, Long> {

    Optional<MemberSnapshotEntity> findByMemberIdAndInsightId(UUID memberId, UUID insightId);
    Page<MemberSnapshotEntity> findAllByMemberId(UUID memberId, Pageable pageable);


    // TODO - embedded query
//    @Query("select ms from MemberSnapshotEntity ms inner join SnapshotEntity s on ms.snapshotId = s.id " +
//            "where ms.memberId = :memberId and s.apartmentComplex.key = :apartmentComplexKey")
//    Page<MemberSnapshotEntity> findAllByMemberIdAndApartmentComplex(UUID memberId, String apartmentComplexKey, PageRequest pageRequest);

    void deleteByMemberIdAndInsightId(UUID memberId, UUID insightId);
}
