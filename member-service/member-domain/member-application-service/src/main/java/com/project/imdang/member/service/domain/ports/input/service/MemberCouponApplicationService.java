package com.project.imdang.member.service.domain.ports.input.service;

import com.project.imdang.member.service.domain.dto.coupon.*;

import java.util.UUID;

public interface MemberCouponApplicationService {

    // + count
    // 가지고 있는 쿠폰 개수? 조회
    ListMemberCouponResponse listMemberCoupon(UUID memberId);

    // 쿠폰 발행
    void issueMemberCoupon(IssueMemberCouponCommand issueMemberCouponCommand);

    // requestExchange → useMemberCoupon(memberCouponId)
    // 요청 완료 (비동기) → 요청 수락 (비동기)
    // memberCoupon used 상태 변경 : 요청 시 (거절 당하면 상태 재변경 필요) vs 요청 완료 후 "수락되면"
    // 쿠폰 사용
    UseMemberCouponCommandResponse useMemberCoupon(UseMemberCouponCommand useMemberCouponCommand);
    void cancleMemberCoupon(CancleMemberCouponCommand cancleMemberCouponCommand);
}
