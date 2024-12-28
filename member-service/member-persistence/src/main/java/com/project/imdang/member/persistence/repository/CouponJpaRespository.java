package com.project.imdang.member.persistence.repository;

import com.project.imdang.member.persistence.entity.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CouponJpaRespository extends JpaRepository<CouponEntity, UUID> {
    Optional<CouponEntity> findById(UUID couponId);
}
