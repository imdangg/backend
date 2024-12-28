package com.project.imdang.member.service.domain.dto.coupon;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ListMemberCouponResponse {
    @Schema(description = "보유한 쿠폰 갯수")
    private Integer couponCount;
}
