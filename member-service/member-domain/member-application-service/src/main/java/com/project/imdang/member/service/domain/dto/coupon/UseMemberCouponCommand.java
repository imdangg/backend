package com.project.imdang.member.service.domain.dto.coupon;

import lombok.*;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UseMemberCouponCommand {
     private UUID memberId;
}

