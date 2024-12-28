package com.project.imdang.member.service.domain.dto.coupon;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UseMemberCouponCommandResponse {
     @Schema(description = "사용한 쿠폰ID")
     private Long memberCouponId;
}

