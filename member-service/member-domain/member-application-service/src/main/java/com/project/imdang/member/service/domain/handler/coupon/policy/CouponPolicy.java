package com.project.imdang.member.service.domain.handler.coupon.policy;

import com.project.imdang.member.service.domain.entity.Coupon;
import com.project.imdang.member.service.domain.entity.Member;

public interface CouponPolicy {

    Integer apply(Coupon coupon, Member member);

    void validate(Coupon coupon, Member member);
}
