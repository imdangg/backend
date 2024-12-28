package com.project.imdang.member.service.domain.handler.coupon;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.MemberCouponDomainService;
import com.project.imdang.member.service.domain.dto.coupon.UseMemberCouponCommand;
import com.project.imdang.member.service.domain.dto.coupon.UseMemberCouponCommandResponse;
import com.project.imdang.member.service.domain.entity.MemberCoupon;
import com.project.imdang.member.service.domain.exception.MemberCouponDomainException;
import com.project.imdang.member.service.domain.mapper.MemberCouponDataMapper;
import com.project.imdang.member.service.domain.ports.output.MemberCouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class UseMemberCouponCommandHandler {
    private final MemberCouponRepository memberCouponRepository;
    private final MemberCouponDomainService memberCouponDomainService;
    private final MemberCouponDataMapper memberCouponDataMapper;

    public UseMemberCouponCommandResponse use(UseMemberCouponCommand useMemberCouponCommand) {
        // 1. 쿠폰 조회
        MemberCoupon memberCoupon = checkMemberCoupon(useMemberCouponCommand.getMemberId());
        // 2. 쿠폰 사용
        memberCouponDomainService.use(memberCoupon);
        log.info("MemberCoupon[id:{}] is used", memberCoupon.getId().getValue());
        return memberCouponDataMapper.memberCouponToUseMemberCouponResponse(memberCoupon);
    }

    private MemberCoupon checkMemberCoupon(UUID _memberId) {
        MemberId memberId = new MemberId(_memberId);
        List<MemberCoupon> memberCoupons = memberCouponRepository.findAllByMemberIdAndUsed(memberId, Boolean.FALSE);
        if (memberCoupons.isEmpty()) {
            String errorMessage = "No more coupons available!";
            log.info(errorMessage);
            throw new MemberCouponDomainException(errorMessage);
        }
        return memberCoupons.get(0);
    }
}
