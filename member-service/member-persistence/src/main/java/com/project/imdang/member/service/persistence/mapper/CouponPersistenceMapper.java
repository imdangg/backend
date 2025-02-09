package com.project.imdang.member.service.persistence.mapper;


import com.project.imdang.domain.valueobject.CouponId;
import com.project.imdang.member.service.domain.entity.Coupon;
import com.project.imdang.member.service.persistence.entity.CouponEntity;
import org.springframework.stereotype.Component;

@Component
public class CouponPersistenceMapper {

    public CouponEntity CouponToCouponEntity(Coupon coupon) {
        return CouponEntity.builder()
                .id(coupon.getId().getValue())
                .name(coupon.getName())
                .expirationDate(coupon.getExpirationDate())
                .build();
    }

    public Coupon couponEntityToCoupon(CouponEntity couponEntity) {
        return Coupon.builder()
                .id(new CouponId(couponEntity.getId()))
                .name(couponEntity.getName())
                .expirationDate(couponEntity.getExpirationDate())
                .build();
    }
}
