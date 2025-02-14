package com.project.imdang.member.service.domain;

import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.entity.MemberCoupon;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberCouponDomainServiceImpl implements MemberCouponDomainService {

    @Override
    public void issue(MemberCoupon memberCoupon) {
        memberCoupon.initialize();
    }

    @Override
    public MemberCoupon use(MemberCoupon memberCoupon) {
        log.info("MemberCoupon[id:{}] is used.", memberCoupon.getId().getValue());
        return memberCoupon.use();
    }

    @Override
    public void cancel(MemberCoupon memberCoupon) {
        memberCoupon.cancel();
        log.info("MemberCoupon[id:{}] is cancelled.", memberCoupon.getId().getValue());
    }
}
