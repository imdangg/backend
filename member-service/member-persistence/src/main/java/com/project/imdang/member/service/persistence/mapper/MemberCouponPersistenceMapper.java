package com.project.imdang.member.service.persistence.mapper;


import com.project.imdang.domain.valueobject.CouponId;
import com.project.imdang.domain.valueobject.MemberCouponId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.entity.MemberCoupon;
import com.project.imdang.member.service.persistence.entity.MemberCouponEntity;
import org.springframework.stereotype.Component;

@Component
public class MemberCouponPersistenceMapper {
    public MemberCouponEntity memberCouponToMemberCouponEntity(MemberCoupon memberCoupon) {
        return MemberCouponEntity.builder()
                .id(memberCoupon.getId().getValue() != null ? memberCoupon.getId().getValue() : null)
                .couponId(memberCoupon.getCouponId().getValue())
                .memberId(memberCoupon.getMemberId().getValue())
                .expiredAt(memberCoupon.getExpiredAt())
                .remark(memberCoupon.getRemark())
                .createdAt(memberCoupon.getCreatedAt())
                .used(memberCoupon.isUsed())
                .usedAt(memberCoupon.getUsedAt())
                .build();
    }

    public MemberCoupon memberCouponEntityToMemberCoupon(MemberCouponEntity memberCouponEntity) {
        return MemberCoupon.builder()
                .id(new MemberCouponId(memberCouponEntity.getId()))
                .couponId(new CouponId(memberCouponEntity.getCouponId()))
                .memberId(new MemberId(memberCouponEntity.getMemberId()))
                .expiredAt(memberCouponEntity.getExpiredAt())
                .remark(memberCouponEntity.getRemark())
                .createdAt(memberCouponEntity.getCreatedAt())
                .used(memberCouponEntity.isUsed())
                .usedAt(memberCouponEntity.getUsedAt())
                .build();
    }
}
