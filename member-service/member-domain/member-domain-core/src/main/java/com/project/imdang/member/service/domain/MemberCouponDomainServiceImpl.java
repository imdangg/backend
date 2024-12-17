package com.project.imdang.member.service.domain;

import com.project.imdang.member.service.domain.entity.MemberCoupon;

public class MemberCouponDomainServiceImpl implements MemberCouponDomainService{
    @Override
    public void issue(MemberCoupon memberCoupon) {
        memberCoupon.initialize();
    }

    @Override
    public void use(MemberCoupon memberCoupon) {
        memberCoupon.use();
    }

    @Override
    public void cancle(MemberCoupon memberCoupon) {
        memberCoupon.cancle();
    }
}
