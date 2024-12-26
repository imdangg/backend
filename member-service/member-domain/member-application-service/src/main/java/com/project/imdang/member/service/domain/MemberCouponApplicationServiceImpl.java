package com.project.imdang.member.service.domain;

import com.project.imdang.member.service.domain.dto.coupon.*;
import com.project.imdang.member.service.domain.handler.coupon.CancelMemberCouponCommandHandler;
import com.project.imdang.member.service.domain.handler.coupon.IssueMemberCouponCommandHandler;
import com.project.imdang.member.service.domain.handler.coupon.ListMemberCouponHandler;
import com.project.imdang.member.service.domain.handler.coupon.UseMemberCouponCommandHandler;
import com.project.imdang.member.service.domain.ports.input.service.MemberCouponApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberCouponApplicationServiceImpl implements MemberCouponApplicationService {

    private final IssueMemberCouponCommandHandler issueMemberCouponCommandHandler;
    private final UseMemberCouponCommandHandler useMemberCouponCommandHandler;
    private final CancelMemberCouponCommandHandler cancelMemberCouponCommandHandler;
    private final ListMemberCouponHandler listMemberCouponHandler;
    @Override
    public ListMemberCouponResponse listMemberCoupon(UUID memberId) {
        return listMemberCouponHandler.list(memberId);
    }

    @Override
    public void issueMemberCoupon(IssueMemberCouponCommand issueMemberCouponCommand) {
        issueMemberCouponCommandHandler.issue(issueMemberCouponCommand);
    }

    @Override
    public UseMemberCouponCommandResponse useMemberCoupon(UseMemberCouponCommand useMemberCouponCommand) {
        return useMemberCouponCommandHandler.use(useMemberCouponCommand);
    }

    @Override
    public void cancleMemberCoupon(CancelMemberCouponCommand cancelMemberCouponCommand) {
        cancelMemberCouponCommandHandler.cancle(cancelMemberCouponCommand);
    }
}
