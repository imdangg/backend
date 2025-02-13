package com.project.imdang.member.service.domain;

import com.project.imdang.member.service.domain.entity.MemberCoupon;

public interface MemberCouponDomainService {
    void issue(MemberCoupon memberCoupon);
    void use(MemberCoupon memberCoupon);
    void cancel(MemberCoupon memberCoupon);
}
