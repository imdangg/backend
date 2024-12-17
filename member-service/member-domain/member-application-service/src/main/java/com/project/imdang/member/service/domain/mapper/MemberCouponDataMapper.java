package com.project.imdang.member.service.domain.mapper;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.dto.coupon.IssueMemberCouponCommand;
import com.project.imdang.member.service.domain.dto.coupon.ListMemberCouponResponse;
import com.project.imdang.member.service.domain.dto.coupon.UseMemberCouponCommandResponse;
import com.project.imdang.member.service.domain.entity.Coupon;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.entity.MemberCoupon;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class MemberCouponDataMapper {

    public ListMemberCouponResponse memberCouponsToListMemberCouponResponse(List<MemberCoupon> memberCoupons) {
        return new ListMemberCouponResponse(memberCoupons.size());
    }

    public List<MemberCoupon> issueMemberCouponCommandToMemberCoupons(Member member, Coupon coupon, Integer quantity) {
        return IntStream.range(0, quantity)
                .mapToObj(i -> MemberCoupon.builder()
                        .memberId(member.getId())
                        .couponId(coupon.getId())
                        .used(Boolean.FALSE)
                        .build()).collect(Collectors.toList());
    }

    public UseMemberCouponCommandResponse memberCouponToUseMemberCouponResponse(MemberCoupon memberCoupon) {
        return new UseMemberCouponCommandResponse(memberCoupon.getId().getValue());
    }
}