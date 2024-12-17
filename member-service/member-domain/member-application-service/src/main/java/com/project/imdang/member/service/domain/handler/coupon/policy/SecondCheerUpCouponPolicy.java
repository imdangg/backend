package com.project.imdang.member.service.domain.handler.coupon.policy;

import com.project.imdang.domain.valueobject.CouponId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.entity.Coupon;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.exception.MemberCouponDomainException;
import com.project.imdang.member.service.domain.ports.output.MemberCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("SecondCheerUp")
@RequiredArgsConstructor
public class SecondCheerUpCouponPolicy implements CouponPolicy{

    private static final Integer REJECT_CRITERIA = 10;
    private static final Integer ISSUE_QUANTITY = 3;
    private final MemberCouponRepository memberCouponRepository;

    @Override
    public Integer apply(Coupon coupon, Member member) {
        return ISSUE_QUANTITY;
    }

    @Override
    public void validate(Coupon coupon, Member member) {
        checkRejectCount(member);
        checkReissue(coupon.getId(), member.getId());
    }

    private Boolean checkReissue(CouponId couponId, MemberId memberId) {
        if(memberCouponRepository.findByCouponIdAndMemberId(couponId, memberId).isPresent()){
            throw new MemberCouponDomainException("Already issued Coupon!");
        }
        return Boolean.TRUE;
    }

    private Boolean checkRejectCount(Member member) {
        if (member.getRejectedCount() < REJECT_CRITERIA) {
            throw new MemberCouponDomainException("Reject Count does not meet criteria");
        }
        return Boolean.TRUE;
    }
}
