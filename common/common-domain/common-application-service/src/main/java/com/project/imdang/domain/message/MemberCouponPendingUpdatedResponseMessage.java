package com.project.imdang.domain.message;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MemberCouponPendingUpdatedResponseMessage {
    private boolean isCompleted;
    private UUID exchangeRequestId;
}
