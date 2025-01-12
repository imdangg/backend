package com.project.imdang.member.service.domain.dto.coupon;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IssueMemberCouponCommand {
    @Setter
    private UUID memberId;
    @NotNull(message = "쿠폰ID를 입력해주세요")
    private UUID couponId;
}
