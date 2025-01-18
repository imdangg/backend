package com.project.imdang.insight.service.persistence.insight.repository;

import com.project.imdang.insight.service.domain.valueobject.Address;
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

    @Query(value = "select ms.* from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id " +
            "where ms.member_id = :memberId and s.address_si_do = :siDo and s.address_si_gun_gu = :siGunGu and s.address_eup_myeon_dong = :eupMyeonDong " +
            "and (:roadName IS NULL OR s.address_road_name = :roadName) " +
            "and (:buildingNumber IS NULL OR s.address_building_number = :buildingNumber) " +
            "and (:detail IS NULL OR s.address_detail = :detail) " +
            "\n-- #pageRequest\n",
            countQuery = "select count(*) from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id " +
                    "where ms.member_id = :memberId and s.address_si_do = :siDo and s.address_si_gun_gu = :siGunGu and s.address_eup_myeon_dong = :eupMyeonDong " +
                    "and (:roadName IS NULL OR s.address_road_name = :roadName) " +
                    "and (:buildingNumber IS NULL OR s.address_building_number = :buildingNumber) " +
                    "and (:detail IS NULL OR s.address_detail = :detail)",
            nativeQuery = true)
    Page<MemberSnapshotEntity> findAllByMemberIdAndAddress(String memberId, String siDo, String siGunGu, String eupMyeonDong, String roadName, String buildingNumber, String detail, Pageable pageable);

    @Query(value = "select ms.* from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id " +
            "where ms.member_id = :memberId and s.complex_name = :apartmentComplexName \n-- #pageRequest\n",
            countQuery = "select count(*) from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id " +
                    "where ms.member_id = :memberId and s.complex_name = :apartmentComplexName",
            nativeQuery = true)
    Page<MemberSnapshotEntity> findAllByMemberIdAndApartmentComplexName(String memberId, String apartmentComplexName, PageRequest pageRequest);

    @Query(value = "select ms.* from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id " +
            "where ms.member_id = :memberId and s.address_si_do = :siDo and s.address_si_gun_gu = :siGunGu and s.address_eup_myeon_dong = :eupMyeonDong " +
            "and (:roadName IS NULL OR s.address_road_name = :roadName) " +
            "and (:buildingNumber IS NULL OR s.address_building_number = :buildingNumber) " +
            "and (:detail IS NULL OR s.address_detail = :detail) " +
            "and s.member_id = :snapshotMemberId \n-- #pageRequest\n",
            countQuery = "select count(*) from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id " +
                    "where ms.member_id = :memberId and s.address_si_do = :siDo and s.address_si_gun_gu = :siGunGu and s.address_eup_myeon_dong = :eupMyeonDong " +
                    "and (:roadName IS NULL OR s.address_road_name = :roadName) " +
                    "and (:buildingNumber IS NULL OR s.address_building_number = :buildingNumber) " +
                    "and (:detail IS NULL OR s.address_detail = :detail) " +
                    "and s.member_id = :snapshotMemberId ",
            nativeQuery = true)
    Page<MemberSnapshotEntity> findAllByMemberIdAndAddressAndSnapshotMemberId(String memberId, String siDo, String siGunGu, String eupMyeonDong, String roadName, String buildingNumber, String detail, UUID snapshotMemberId, Pageable pageable);

    @Query(value = "select ms.* from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id " +
            "where ms.member_id = :memberId and s.complex_name = :apartmentComplexName and s.member_id = :snapshotMemberId \n-- #pageRequest\n",
            countQuery = "select count(*) from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id " +
                    "where ms.member_id = :memberId and s.complex_name = :apartmentComplexName and s.member_id = :snapshotMemberId",
            nativeQuery = true)
    Page<MemberSnapshotEntity> findAllByMemberIdAndApartmentComplexNameAndSnapshotMemberId(String memberId, String apartmentComplexName, UUID snapshotMemberId, PageRequest pageRequest);

    @Query(value = "select distinct s.address_si_do, s.address_si_gun_gu, s.address_eup_myeon_dong " +
//            ", s.address_road_name, s.address_building_number, s.address_detail " +
            "from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id where ms.member_id = :memberId",
            nativeQuery = true)
    List<Object[]> findAllDistinctAddressByMemberId(String memberId);

    @Query(value = "select distinct s.complex_name from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id " +
            "where ms.member_id = :memberId and s.address_si_do = :siDo and s.address_si_gun_gu = :siGunGu and s.address_eup_myeon_dong = :eupMyeonDong " +
            "and (:roadName IS NULL OR s.address_road_name = :roadName) " +
            "and (:buildingNumber IS NULL OR s.address_building_number = :buildingNumber) " +
            "and (:detail IS NULL OR s.address_detail = :detail)",
            nativeQuery = true)
    List<String> findAllDistinctApartmentComplexByMemberIdAndAddress(String memberId, String siDo, String siGunGu, String eupMyeonDong, String roadName, String buildingNumber, String detail);

    @Query(value = "select count(*) from member_snapshot ms inner join snapshot s on ms.snapshot_id = s.id " +
            "where ms.member_id = :memberId and s.address_si_do = :siDo and s.address_si_gun_gu = :siGunGu and s.address_eup_myeon_dong = :eupMyeonDong " +
            "and (:roadName IS NULL OR s.address_road_name = :roadName) " +
            "and (:buildingNumber IS NULL OR s.address_building_number = :buildingNumber) " +
            "and (:detail IS NULL OR s.address_detail = :detail) ",
            nativeQuery = true)
    int countAllByMemberIdAndAddress(String memberId, String siDo, String siGunGu, String eupMyeonDong, String roadName, String buildingNumber, String detail);

    void deleteByMemberIdAndInsightId(UUID memberId, UUID insightId);
}
