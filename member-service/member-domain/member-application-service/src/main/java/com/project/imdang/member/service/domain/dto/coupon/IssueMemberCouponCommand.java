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
    @NotNull
    private UUID couponId;
}
