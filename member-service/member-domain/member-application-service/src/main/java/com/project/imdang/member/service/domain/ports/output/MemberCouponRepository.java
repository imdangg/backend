package com.project.imdang.member.service.domain.ports.output;

import com.project.imdang.domain.valueobject.CouponId;
import com.project.imdang.domain.valueobject.MemberCouponId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.entity.MemberCoupon;

import java.util.List;
import java.util.Optional;

public interface MemberCouponRepository {
    List<MemberCoupon> findAllByMemberIdAndUsed(MemberId memberId, Boolean used);

    List<MemberCoupon> saveAll(List<MemberCoupon> memberCoupons);

    Optional<MemberCoupon> findByCouponIdAndMemberId(CouponId couponId, MemberId memberId);

    Optional<MemberCoupon> findById(MemberCouponId memberCouponId);
}
