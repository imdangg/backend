package com.project.imdang.member.persistence.mapper;


import com.project.imdang.domain.valueobject.CouponId;
import com.project.imdang.domain.valueobject.MemberCouponId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.persistence.entity.MemberCouponEntity;
import com.project.imdang.member.persistence.entity.MemberEntity;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.entity.MemberCoupon;
import org.springframework.stereotype.Component;

@Component
public class MemberCouponPersistenceMapper {
    public MemberCouponEntity memberCouponToMemberCouponEntity(MemberCoupon memberCoupon) {
        return MemberCouponEntity.builder()
                .couponId(memberCoupon.getCouponId().getValue())
                .memberId(memberCoupon.getMemberId().getValue())
                .createdAt(memberCoupon.getCreatedAt())
                .used(memberCoupon.getUsed())
                .remark(memberCoupon.getRemark())
                .usedAt(memberCoupon.getUsedAt())
                .build();
    }

    public MemberCoupon memberCouponEntityToMemberCoupon(MemberCouponEntity memberCouponEntity) {
        return MemberCoupon.builder()
                .id(new MemberCouponId(memberCouponEntity.getId()))
                .couponId(new CouponId(memberCouponEntity.getCouponId()))
                .memberId(new MemberId(memberCouponEntity.getMemberId()))
                .createdAt(memberCouponEntity.getCreatedAt())
                .used(memberCouponEntity.getUsed())
                .remark(memberCouponEntity.getRemark())
                .usedAt(memberCouponEntity.getUsedAt())
                .build();
    }
}
