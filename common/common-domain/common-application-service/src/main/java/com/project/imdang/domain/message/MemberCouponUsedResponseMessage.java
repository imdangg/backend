package com.project.imdang.domain.message;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MemberCouponUsedResponseMessage {
    private boolean isCompleted;
    private Long memberCouponId;
}
