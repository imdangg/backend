package com.project.imdang.insight.service.domain.ports.input.listener;

import com.project.imdang.domain.message.MemberCouponCancelledResponseMessage;

public interface MemberCouponCancelledResponseMessageListener {
    void updated(MemberCouponCancelledResponseMessage memberCouponCancelledResponseMessage);
}
