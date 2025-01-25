package com.project.imdang.insight.service.domain.ports.input.listener;

import com.project.imdang.domain.message.MemberCouponUsedResponseMessage;

public interface MemberCouponUsedResponseMessageListener {
    void updated(MemberCouponUsedResponseMessage memberCouponUsedResponseMessage);
}
