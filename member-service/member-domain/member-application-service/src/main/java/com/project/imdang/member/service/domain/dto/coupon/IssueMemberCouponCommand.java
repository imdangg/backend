package com.project.imdang.member.service.domain.dto.coupon;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IssueMemberCouponCommand {
    @Setter
    private UUID memberId;
    @NotNull
    private String name;
}
