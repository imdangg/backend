package com.project.imdang.member.service.domain.handler.coupon;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.dto.coupon.DetailMyCouponResponse;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.entity.MemberCoupon;
import com.project.imdang.member.service.domain.handler.MemberHelper;
import com.project.imdang.member.service.domain.mapper.MemberCouponDataMapper;
import com.project.imdang.member.service.domain.ports.output.MemberCouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class DetailMyCouponHandler {

    private final MemberHelper memberHelper;
    private final MemberCouponRepository memberCouponRepository;
    private final MemberCouponDataMapper memberCouponDataMapper;

    @Transactional(readOnly = true)
    public DetailMyCouponResponse detailMyCoupon(UUID _memberId) {

        MemberId memberId = new MemberId(_memberId);
        Member member = memberHelper.get(memberId);
        // MemberCoupon 조회
        List<MemberCoupon> memberCoupons = memberCouponRepository.findAllByMemberIdAndUsed(member.getId(), Boolean.FALSE);
        log.info("Member[id:{}] have {} Coupons", member.getId().getValue(), memberCoupons.size());
        return memberCouponDataMapper.memberCouponsToDetailMyCouponResponse(memberCoupons);
    }
}
