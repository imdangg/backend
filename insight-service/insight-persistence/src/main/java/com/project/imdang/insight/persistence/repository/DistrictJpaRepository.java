package com.project.imdang.insight.persistence.repository;

import com.project.imdang.insight.persistence.entity.DistrictEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DistrictJpaRepository extends JpaRepository<DistrictEntity, String> {

    @Query("select distinct d.code, d.siDo, d.siGunGu from DistrictEntity d " +
            "where d.siDo = :siDo and d.siGunGu <> '' and d.eupMyeonDong = '' and d.deletedAt is null")
    Page<Object[]> findAllSiGunGuBySiDo(@Param("siDo") String siDo, Pageable pageable);

    @Query("select distinct d.code, d.siDo, d.siGunGu, d.eupMyeonDong from DistrictEntity d " +
            "where d.siDo = :siDo and d.siGunGu = :siGunGu and d.eupMyeonDong <> '' and d.deletedAt is null")
    Page<Object[]> findAllEupMyeonDongBySiDoAndSiGunGu(@Param("siDo") String siDo, @Param("siGunGu") String siGunGu, Pageable pageable);
}
