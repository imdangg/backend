package com.project.imdang.member.service.persistence.adapter;

import com.project.imdang.domain.valueobject.CouponId;
import com.project.imdang.member.service.persistence.mapper.CouponPersistenceMapper;
import com.project.imdang.member.service.persistence.repository.CouponJpaRepository;
import com.project.imdang.member.service.domain.entity.Coupon;
import com.project.imdang.member.service.domain.ports.output.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CouponRepositoryImpl implements CouponRepository {
    private final CouponJpaRepository couponJpaRepository;
    private final CouponPersistenceMapper couponPersistenceMapper;

    @Override
    public Optional<Coupon> findById(CouponId couponId) {
        return couponJpaRepository.findById(couponId.getValue())
                .map(couponPersistenceMapper::couponEntityToCoupon);
    }

    @Override
    public Optional<Coupon> findByName(String name) {
        return couponJpaRepository.findByName(name)
                .map(couponPersistenceMapper::couponEntityToCoupon);
    }
}
