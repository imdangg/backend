package com.project.imdang.member.service.domain.dto.coupon;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IssueMemberCouponCommand {
    private UUID memberId;
    private UUID couponId;
}
