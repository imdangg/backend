package com.project.imdang.member.service.domain.ports.output;

import com.project.imdang.domain.valueobject.CouponId;
import com.project.imdang.member.service.domain.entity.Coupon;

import java.util.Optional;

public interface CouponRepository {
    Optional<Coupon> findById(CouponId couponId);
}
