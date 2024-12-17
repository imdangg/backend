package com.project.imdang.member.persistence.repository;

import com.project.imdang.member.persistence.entity.MemberCouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberCouponJpaRespository extends JpaRepository<MemberCouponEntity, Long> {
    List<MemberCouponEntity> findAllByMemberIdAndUsed(UUID memberId, Boolean used);
    Optional<MemberCouponEntity> findByCouponIdAndMemberId(UUID couponId, UUID memberId);
}
