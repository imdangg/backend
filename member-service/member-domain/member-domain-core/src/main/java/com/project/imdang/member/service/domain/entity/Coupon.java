package com.project.imdang.member.service.domain.entity;


import com.project.imdang.domain.entity.AggregateRoot;
import com.project.imdang.domain.valueobject.CouponId;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Coupon extends AggregateRoot<CouponId> {
    private String name;
    private int count;
    // 초기, 3
    // cheer-up, 1/3/5
    private String expirationDate;

    @Builder
    public Coupon(CouponId id, String name, String expirationDate) {
        setId(id);
        this.name = name;
        this.expirationDate = expirationDate;
    }
}
