package com.project.imdang.member.service.domain.handler.coupon;

import com.project.imdang.domain.valueobject.MemberCouponId;
import com.project.imdang.member.service.domain.MemberCouponDomainService;
import com.project.imdang.member.service.domain.dto.coupon.CancelMemberCouponCommand;
import com.project.imdang.member.service.domain.entity.MemberCoupon;
import com.project.imdang.member.service.domain.exception.MemberCouponNotFoundException;
import com.project.imdang.member.service.domain.ports.output.MemberCouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class CancelMemberCouponCommandHandler {
    private final MemberCouponRepository memberCouponRepository;
    private final MemberCouponDomainService memberCouponDomainService;

    public void cancle(CancelMemberCouponCommand cancelMemberCouponCommand) {
        // 1. 쿠폰 조회
        MemberCoupon memberCoupon = checkMemberCoupon(cancelMemberCouponCommand.getMemberCouponId());
        // 2. 쿠폰 취소
        memberCouponDomainService.cancle(memberCoupon);
        log.info("MemberCoupon[id:{}] is cancled", memberCoupon.getId().getValue());
    }

    private MemberCoupon checkMemberCoupon(Long _memberCouponId) {
        MemberCouponId memberCouponId = new MemberCouponId(_memberCouponId);
        return memberCouponRepository.findById(memberCouponId)
                .orElseThrow(() -> new MemberCouponNotFoundException(memberCouponId));
    }
}
