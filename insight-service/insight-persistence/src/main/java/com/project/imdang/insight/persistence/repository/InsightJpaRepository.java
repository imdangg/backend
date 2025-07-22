package com.project.imdang.insight.persistence.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.project.imdang.common.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.persistence.entity.InsightEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    List<ApartmentComplex> findDistinctApartmentComplexByMemberId(@Param("memberId") UUID memberId);

    @Query(value = "select distinct i.address_si_do, i.address_si_gun_gu, i.address_eup_myeon_dong " +
            "from insight i " +
            "left join recommend r on i.id = r.recommended_insight_id " +
            "where i.member_id = :memberId or r.recommend_member_id = :memberId",
            nativeQuery = true)
    List<Object[]> findAllDistrictByMemberId(@Param("memberId") String memberId);

    @Query(value = "select COUNT(distinct i.complex_name) AS apartment_complex_count, " +
            "COUNT(*) as insight_count " +
            "from insight i " +
            "left join recommend r on i.id = r.recommended_insight_id " +
            "where (i.member_id = :memberId or r.recommend_member_id = :memberId) " +
            "and i.address_si_do = :siDo " +
            "and i.address_si_gun_gu = :siGunGu " +
            "and i.address_eup_myeon_dong = :eupMyeonDong",
            nativeQuery = true)
    Tuple countAllByMemberIdAndDistrict(@Param("memberId") String memberId,
                                        @Param("siDo") String siDo,
                                        @Param("siGunGu") String siGunGu,
                                        @Param("eupMyeonDong") String eupMyeonDong);

    @Query(value = "select i.complex_name, COUNT(*) " +
            "from insight i " +
            "left join recommend r on i.id = r.recommended_insight_id " +
            "where (i.member_id = :memberId or r.recommend_member_id = :memberId) " +
            "and i.address_si_do = :siDo " +
            "and i.address_si_gun_gu = :siGunGu " +
            "and i.address_eup_myeon_dong = :eupMyeonDong " +
            "group by i.complex_name", nativeQuery = true)
    List<Object[]> findAllApartmentComplexAndInsightCountByMemberIdAndDistrict(@Param("memberId") String memberId,
                                                                               @Param("siDo") String siDo,
                                                                               @Param("siGunGu") String siGunGu,
                                                                               @Param("eupMyeonDong") String eupMyeonDong);
    @Query(value = "select i.* from insight i " +
            "where (i.member_id = :memberId) " +
            "and i.complex_name = :apartmentComplexName \n-- #pageRequest\n",
            countQuery = "select count(*) from insight i " +
                    "where i.member_id = :memberId " +
                    "and i.complex_name = :apartmentComplexName",
            nativeQuery = true)
    Page<InsightEntity> findAllByMemberIdAndApartmentComplexAndOnlyMine(@Param("memberId") String memberId, @Param("apartmentComplexName") String apartmentComplexName, PageRequest pageRequest);

    @Query(value = "select i.* from insight i left join recommend r on i.id = r.recommended_insight_id " +
            "where (i.member_id = :memberId or r.recommend_member_id = :memberId) " +
            "and i.complex_id = :apartmentComplexName \n-- #pageRequest\n",
            countQuery = "select count(*) from insight i left join recommend r on i.id = r.recommended_insight_id " +
                    "where (i.member_id = :memberId or r.recommend_member_id = :memberId) " +
                    "and i.complex_name = :apartmentComplexName",
            nativeQuery = true)
    Page<InsightEntity> findAllByMemberIdAndApartmentComplex(@Param("memberId") String memberId, @Param("apartmentComplexName") String apartmentComplexName, PageRequest pageRequest);

    @Query(value = "select i.* from insight i " +
            "where i.member_id = :memberId " +
            "and i.address_si_do = :siDo and i.address_si_gun_gu = :siGunGu and i.address_eup_myeon_dong = :eupMyeonDong \n-- #pageRequest\n",
            countQuery = "select count(*) from insight i " +
                    "where i.member_id = :memberId " +
                    "and i.address_si_do = :siDo and i.address_si_gun_gu = :siGunGu and i.address_eup_myeon_dong = :eupMyeonDong ",
            nativeQuery = true)
    Page<InsightEntity> findAllByMemberIdAndDistrictAndOnlyMine(@Param("memberId") String memberId,
                                                 @Param("siDo") String siDo,
                                                 @Param("siGunGu") String siGunGu,
                                                 @Param("eupMyeonDong") String eupMyeonDong, Pageable pageable);

    @Query(value = "select i.* from insight i left join recommend r on i.id = r.recommended_insight_id " +
            "where (i.member_id = :memberId or r.recommend_member_id = :memberId) " +
            "and i.address_si_do = :siDo and i.address_si_gun_gu = :siGunGu and i.address_eup_myeon_dong = :eupMyeonDong \n-- #pageRequest\n",
            countQuery = "select count(*) from insight i left join recommend r on i.id = r.recommended_insight_id " +
                    "where (i.member_id = :memberId or r.recommend_member_id = :memberId) " +
                    "and i.address_si_do = :siDo and i.address_si_gun_gu = :siGunGu and i.address_eup_myeon_dong = :eupMyeonDong ",
            nativeQuery = true)
    Page<InsightEntity> findAllByMemberIdAndDistrict(@Param("memberId") String memberId,
                                                     @Param("siDo") String siDo,
                                                     @Param("siGunGu") String siGunGu,
                                                     @Param("eupMyeonDong") String eupMyeonDong, Pageable pageable);

}
