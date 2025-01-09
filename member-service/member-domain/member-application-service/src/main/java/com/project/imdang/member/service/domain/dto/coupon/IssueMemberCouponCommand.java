package com.project.imdang.member.service.domain.dto.coupon;

import lombok.*;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IssueMemberCouponCommand {
    @Setter
    private UUID memberId;
    private UUID couponId;
}
