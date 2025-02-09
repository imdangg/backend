package com.project.imdang.member.service.domain.ports.input.service;

import com.project.imdang.member.service.domain.dto.coupon.IssueMemberCouponCommand;
import com.project.imdang.member.service.domain.dto.coupon.DetailMyCouponResponse;

import java.util.UUID;

public interface MemberCouponApplicationService {

    DetailMyCouponResponse detailMyCoupon(UUID memberId);

    // 쿠폰 발행
    void issueMemberCoupon(IssueMemberCouponCommand issueMemberCouponCommand);
}
