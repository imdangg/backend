package com.project.imdang.member.service.domain.handler.coupon.policy;

import com.project.imdang.domain.valueobject.CouponId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.entity.Coupon;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.exception.MemberCouponDomainException;
import com.project.imdang.member.service.domain.ports.output.MemberCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("Welcome")
@RequiredArgsConstructor
public class JoinedCouponPolicy implements CouponPolicy{
    private static final Integer ISSUE_QUANTITY = 3;
    private final MemberCouponRepository memberCouponRepository;

    @Override
    public Integer apply(Coupon coupon, Member member) {
        validate(coupon, member);
        return ISSUE_QUANTITY;
    }

    @Override
    public void validate(Coupon coupon, Member member) {
        checkReissue(coupon.getId(), member.getId());
    }

    // 재발급 검사
    private Boolean checkReissue(CouponId couponId, MemberId memberId) {
        if(memberCouponRepository.findByCouponIdAndMemberId(couponId, memberId).isPresent()){
            throw new MemberCouponDomainException("Already issued Coupon!");
        }
        return Boolean.TRUE;
    }
}
