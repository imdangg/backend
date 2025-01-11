package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.domain.message.MemberCouponPendingUpdatedResponseMessage;

public interface MemberCouponPendingUpdatedResponseMessageListener {
    void updated(MemberCouponPendingUpdatedResponseMessage memberCouponPendingUpdatedResponseMessage);
}
