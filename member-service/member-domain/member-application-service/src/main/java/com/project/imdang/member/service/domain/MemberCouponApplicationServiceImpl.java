package com.project.imdang.member.service.domain;

import com.project.imdang.member.service.domain.dto.coupon.IssueMemberCouponCommand;
import com.project.imdang.member.service.domain.dto.coupon.DetailMyCouponResponse;
import com.project.imdang.member.service.domain.handler.coupon.IssueMemberCouponCommandHandler;
import com.project.imdang.member.service.domain.handler.coupon.DetailMyCouponHandler;
import com.project.imdang.member.service.domain.ports.input.service.MemberCouponApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberCouponApplicationServiceImpl implements MemberCouponApplicationService {

    private final IssueMemberCouponCommandHandler issueMemberCouponCommandHandler;
    private final DetailMyCouponHandler detailMyCouponHandler;

    @Override
    public DetailMyCouponResponse detailMyCoupon(UUID memberId) {
        return detailMyCouponHandler.detailMyCoupon(memberId);
    }

    @Override
    public void issueMemberCoupon(IssueMemberCouponCommand issueMemberCouponCommand) {
        issueMemberCouponCommandHandler.issue(issueMemberCouponCommand);
    }
}
