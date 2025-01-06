package com.project.imdang.insight.service.persistence.insight.repository;

import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.persistence.insight.entity.MemberSnapshotEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberSnapshotJpaRepository extends JpaRepository<MemberSnapshotEntity, Long> {

    Optional<MemberSnapshotEntity> findByMemberIdAndInsightId(UUID memberId, UUID insightId);
    Page<MemberSnapshotEntity> findAllByMemberId(UUID memberId, Pageable pageable);

    // TODO - embedded query
    @Query(value = "select ms.* from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id " +
            "where ms.member_id = :memberId and s.complex_key = :apartmentComplexKey \n-- #pageRequest\n",
            countQuery = "select count(*) from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id " +
                    "where ms.member_id = :memberId and s.complex_key = :apartmentComplexKey",
            nativeQuery = true)
    Page<MemberSnapshotEntity> findAllByMemberIdAndApartmentComplex(UUID memberId, String apartmentComplexKey, PageRequest pageRequest);

    @Query(value = "select ms from MemberSnapshotEntity ms inner join SnapshotEntity s on ms.snapshotId = s.id where ms.memberId = :memberId and s.memberId = :snapshotMemberId")
    Page<MemberSnapshotEntity> findAllByMemberIdAndSnapshotMemberId(UUID memberId, UUID snapshotMemberId, PageRequest pageRequest);

    @Query(value = "select ms.* from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id " +
            "where ms.member_id = :memberId and s.complex_key = :apartmentComplexKey and s.member_id = :snapshotMemberId \n-- #pageRequest\n",
            countQuery = "select count(*) from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id " +
                    "where ms.member_id = :memberId and s.complex_key = :apartmentComplexKey and s.member_id = :snapshotMemberId",
            nativeQuery = true)
    Page<MemberSnapshotEntity> findAllByMemberIdAndApartmentComplexAndSnapshotMemberId(UUID memberId, String apartmentComplexKey, UUID snapshotMemberId, PageRequest pageRequest);

    // TODO - paging
    @Query(value = "select distinct s.apartmentComplex from MemberSnapshotEntity ms inner join SnapshotEntity s on ms.snapshotId = s.id where ms.memberId = :memberId")
    List<ApartmentComplex> findAllDistinctApartmentComplexByMemberId(UUID memberId);

    void deleteByMemberIdAndInsightId(UUID memberId, UUID insightId);
}
