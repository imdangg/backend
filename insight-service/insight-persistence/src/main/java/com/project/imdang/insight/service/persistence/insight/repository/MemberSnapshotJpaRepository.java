package com.project.imdang.insight.service.persistence.insight.repository;

import com.project.imdang.insight.service.persistence.insight.entity.MemberSnapshotEntity;
import jakarta.persistence.Tuple;
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

    @Query(value = "select ms.* from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id " +
            "where ms.member_id = :memberId and s.address_si_do = :siDo and s.address_si_gun_gu = :siGunGu and s.address_eup_myeon_dong = :eupMyeonDong \n-- #pageRequest\n",
            countQuery = "select count(*) from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id " +
                    "where ms.member_id = :memberId and s.address_si_do = :siDo and s.address_si_gun_gu = :siGunGu and s.address_eup_myeon_dong = :eupMyeonDong",
            nativeQuery = true)
    Page<MemberSnapshotEntity> findAllByMemberIdAndDistrict(String memberId, String siDo, String siGunGu, String eupMyeonDong, Pageable pageable);

    @Query(value = "select ms.* from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id " +
            "where ms.member_id = :memberId and s.complex_name = :apartmentComplexName \n-- #pageRequest\n",
            countQuery = "select count(*) from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id " +
                    "where ms.member_id = :memberId and s.complex_name = :apartmentComplexName",
            nativeQuery = true)
    Page<MemberSnapshotEntity> findAllByMemberIdAndApartmentComplexName(String memberId, String apartmentComplexName, PageRequest pageRequest);

    @Query(value = "select ms.* from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id " +
            "where ms.member_id = :memberId and s.address_si_do = :siDo and s.address_si_gun_gu = :siGunGu and s.address_eup_myeon_dong = :eupMyeonDong " +
            "and s.member_id = :snapshotMemberId \n-- #pageRequest\n",
            countQuery = "select count(*) from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id " +
                    "where ms.member_id = :memberId and s.address_si_do = :siDo and s.address_si_gun_gu = :siGunGu and s.address_eup_myeon_dong = :eupMyeonDong " +
                    "and s.member_id = :snapshotMemberId ",
            nativeQuery = true)
    Page<MemberSnapshotEntity> findAllByMemberIdAndDistrictAndSnapshotMemberId(String memberId, String siDo, String siGunGu, String eupMyeonDong, String snapshotMemberId, Pageable pageable);

    @Query(value = "select ms.* from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id " +
            "where ms.member_id = :memberId and s.complex_name = :apartmentComplexName and s.member_id = :snapshotMemberId \n-- #pageRequest\n",
            countQuery = "select count(*) from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id " +
                    "where ms.member_id = :memberId and s.complex_name = :apartmentComplexName and s.member_id = :snapshotMemberId",
            nativeQuery = true)
    Page<MemberSnapshotEntity> findAllByMemberIdAndApartmentComplexNameAndSnapshotMemberId(String memberId, String apartmentComplexName, String snapshotMemberId, PageRequest pageRequest);

    @Query(value = "select distinct s.address_si_do, s.address_si_gun_gu, s.address_eup_myeon_dong " +
            "from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id where ms.member_id = :memberId",
            nativeQuery = true)
    List<Object[]> findAllDistinctDistrictByMemberId(String memberId);

    @Query(value = "select s.complex_name, count(*) from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id " +
            "where ms.member_id = :memberId and s.address_si_do = :siDo and s.address_si_gun_gu = :siGunGu and s.address_eup_myeon_dong = :eupMyeonDong " +
            "group by s.complex_name",
            nativeQuery = true)
    List<Object[]> findAllDistinctApartmentComplexAndInsightCountByMemberIdAndDistrict(String memberId, String siDo, String siGunGu, String eupMyeonDong);

    @Query(value = "select count(distinct s.complex_name) as apartment_complex_count, count(*) as insight_count from member_snapshot ms " +
            "inner join snapshot s on ms.snapshot_id = s.id " +
            "where ms.member_id = :memberId and s.address_si_do = :siDo and s.address_si_gun_gu = :siGunGu and s.address_eup_myeon_dong = :eupMyeonDong ",
            nativeQuery = true)
    Tuple countAllByMemberIdAndDistrict(String memberId, String siDo, String siGunGu, String eupMyeonDong);

    void deleteByMemberIdAndInsightId(UUID memberId, UUID insightId);
}
