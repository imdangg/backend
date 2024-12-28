package com.project.imdang.member.service.domain;

import com.project.imdang.member.service.domain.entity.MemberCoupon;

import java.util.List;

public interface MemberCouponDomainService {
    void issue(MemberCoupon memberCoupon);
    void use(MemberCoupon memberCoupon);
    void cancle(MemberCoupon memberCoupon);
}
