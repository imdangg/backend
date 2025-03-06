package com.project.imdang.member.service.persistence.repository;

import com.project.imdang.member.service.persistence.entity.MemberCouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberCouponJpaRepository extends JpaRepository<MemberCouponEntity, Long> {
    List<MemberCouponEntity> findAllByMemberIdAndUsed(UUID memberId, Boolean used);
    Boolean existsByCouponIdAndMemberId(UUID couponId, UUID memberId);
}
