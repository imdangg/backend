package com.project.imdang.member.persistence.adapter;

import com.project.imdang.domain.valueobject.CouponId;
import com.project.imdang.member.persistence.mapper.CouponPersistenceMapper;
import com.project.imdang.member.persistence.repository.CouponJpaRespository;
import com.project.imdang.member.service.domain.entity.Coupon;
import com.project.imdang.member.service.domain.ports.output.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CouponRespositoryImpl implements CouponRepository {
    private final CouponJpaRespository couponJpaRespository;
    private final CouponPersistenceMapper couponPersistenceMapper;

    @Override
    public Optional<Coupon> findById(CouponId couponId) {
        return couponJpaRespository.findById(couponId.getValue())
                .map(couponPersistenceMapper::couponEntityToCoupon);
    }
}
