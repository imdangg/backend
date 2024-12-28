package com.project.imdang.member.service.domain.handler.coupon;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.dto.coupon.ListMemberCouponResponse;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.entity.MemberCoupon;
import com.project.imdang.member.service.domain.exception.MemberNotFoundException;
import com.project.imdang.member.service.domain.mapper.MemberCouponDataMapper;
import com.project.imdang.member.service.domain.ports.output.MemberCouponRepository;
import com.project.imdang.member.service.domain.ports.output.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class ListMemberCouponHandler {

    private final MemberRepository memberRepository;
    private final MemberCouponRepository memberCouponRepository;
    private final MemberCouponDataMapper memberCouponDataMapper;

    public ListMemberCouponResponse list(UUID memberId) {
        //TODO : 추후 페이징 처리
        Member member = checkMember(memberId);
        // MemberCoupon 조회
        List<MemberCoupon> memberCoupons = memberCouponRepository.findAllByMemberIdAndUsed(member.getId(), Boolean.FALSE);
        log.info("");
        return memberCouponDataMapper.memberCouponsToListMemberCouponResponse(memberCoupons);
    }
    private Member checkMember(UUID _memberId) {
        MemberId memberId = new MemberId(_memberId);
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
    }
}
