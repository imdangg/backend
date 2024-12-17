package com.project.imdang.member.persistence.adapter;

import com.project.imdang.domain.valueobject.CouponId;
import com.project.imdang.domain.valueobject.MemberCouponId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.persistence.entity.MemberCouponEntity;
import com.project.imdang.member.persistence.mapper.MemberCouponPersistenceMapper;
import com.project.imdang.member.persistence.repository.MemberCouponJpaRespository;
import com.project.imdang.member.service.domain.entity.MemberCoupon;
import com.project.imdang.member.service.domain.ports.output.MemberCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberCouponRespositoryImpl implements MemberCouponRepository{
    private final MemberCouponJpaRespository memberCouponJpaRespository;
    private final MemberCouponPersistenceMapper memberCouponPersistenceMapper;
    @Override
    public List<MemberCoupon> findAllByMemberIdAndUsed(MemberId memberId, Boolean used) {
        List<MemberCouponEntity> memberCouponEntity = memberCouponJpaRespository.findAllByMemberIdAndUsed(memberId.getValue(), used);
        return memberCouponEntity.stream()
                .map(memberCouponPersistenceMapper::memberCouponEntityToMemberCoupon).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<MemberCoupon> saveAll(List<MemberCoupon> memberCoupons) {
        List<MemberCouponEntity> memberCouponEntities = memberCouponJpaRespository.saveAll(memberCoupons.stream()
                .map(memberCouponPersistenceMapper::memberCouponToMemberCouponEntity).collect(Collectors.toList()));
        return memberCouponEntities.stream()
                .map(memberCouponPersistenceMapper::memberCouponEntityToMemberCoupon).collect(Collectors.toList());
    }


    @Override
    public Optional<MemberCoupon> findByCouponIdAndMemberId(CouponId couponId, MemberId memberId) {
        return memberCouponJpaRespository.findByCouponIdAndMemberId(couponId.getValue(), memberId.getValue())
                .map(memberCouponPersistenceMapper::memberCouponEntityToMemberCoupon);
    }

    @Override
    public Optional<MemberCoupon> findById(MemberCouponId memberCouponId) {
        return memberCouponJpaRespository.findById(memberCouponId.getValue())
                .map(memberCouponPersistenceMapper::memberCouponEntityToMemberCoupon);
    }
}
