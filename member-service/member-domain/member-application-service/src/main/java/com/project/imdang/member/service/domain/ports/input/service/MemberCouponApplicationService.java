package com.project.imdang.member.service.domain.ports.input.service;

public interface MemberCouponApplicationService {

    // + count
    void listMemberCoupon();

    void issueMemberCoupon();

    // requestExchange → useMemberCoupon(memberCouponId)
    // 요청 완료 (비동기) → 요청 수락 (비동기)
    // memberCoupon used 상태 변경 : 요청 시 (거절 당하면 상태 재변경 필요) vs 요청 완료 후 "수락되면"
    void useMemberCoupon();
}
