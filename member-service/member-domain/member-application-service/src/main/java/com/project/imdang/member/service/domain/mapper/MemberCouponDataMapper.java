package com.project.imdang.member.service.domain.mapper;

import com.project.imdang.member.service.domain.dto.coupon.DetailMyCouponResponse;
import com.project.imdang.member.service.domain.entity.Coupon;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.entity.MemberCoupon;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class MemberCouponDataMapper {

    public DetailMyCouponResponse memberCouponsToDetailMyCouponResponse(List<MemberCoupon> memberCoupons) {
        if(memberCoupons.isEmpty()){
            return new DetailMyCouponResponse(0, null);
        }
        return new DetailMyCouponResponse(memberCoupons.size(), memberCoupons.get(0).getId().getValue());
    }

    public List<MemberCoupon> issueMemberCouponCommandToMemberCoupons(Member member, Coupon coupon, Integer quantity) {
        return IntStream.range(0, quantity)
                .mapToObj(i -> MemberCoupon.builder()
                        .memberId(member.getId())
                        .couponId(coupon.getId())
                        .used(Boolean.FALSE)
                        .build()).collect(Collectors.toList());
    }
}
