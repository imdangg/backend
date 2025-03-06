package com.project.imdang.member.service.persistence.adapter;

import com.project.imdang.domain.valueobject.CouponId;
import com.project.imdang.domain.valueobject.MemberCouponId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.entity.MemberCoupon;
import com.project.imdang.member.service.domain.ports.output.MemberCouponRepository;
import com.project.imdang.member.service.persistence.entity.MemberCouponEntity;
import com.project.imdang.member.service.persistence.mapper.MemberCouponPersistenceMapper;
import com.project.imdang.member.service.persistence.repository.MemberCouponJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberCouponRepositoryImpl implements MemberCouponRepository {

    private final MemberCouponJpaRepository memberCouponJpaRepository;
    private final MemberCouponPersistenceMapper memberCouponPersistenceMapper;

    @Override
    public List<MemberCoupon> findAllByMemberIdAndUsed(MemberId memberId, Boolean used) {
        List<MemberCouponEntity> memberCouponEntity = memberCouponJpaRepository.findAllByMemberIdAndUsed(memberId.getValue(), used);
        return memberCouponEntity.stream()
                .map(memberCouponPersistenceMapper::memberCouponEntityToMemberCoupon).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public MemberCoupon save(MemberCoupon memberCoupon) {
        MemberCouponEntity memberCouponEntity = memberCouponPersistenceMapper.memberCouponToMemberCouponEntity(memberCoupon);
        MemberCouponEntity saved = memberCouponJpaRepository.save(memberCouponEntity);
        return memberCouponPersistenceMapper.memberCouponEntityToMemberCoupon(saved);
    }

    @Transactional
    @Override
    public List<MemberCoupon> saveAll(List<MemberCoupon> memberCoupons) {
        List<MemberCouponEntity> memberCouponEntities = memberCouponJpaRepository.saveAll(memberCoupons.stream()
                .map(memberCouponPersistenceMapper::memberCouponToMemberCouponEntity).collect(Collectors.toList()));
        return memberCouponEntities.stream()
                .map(memberCouponPersistenceMapper::memberCouponEntityToMemberCoupon).collect(Collectors.toList());
    }

    @Override
    public Boolean existsByCouponIdAndMemberId(CouponId couponId, MemberId memberId) {
        return memberCouponJpaRepository.existsByCouponIdAndMemberId(couponId.getValue(), memberId.getValue());
    }

    @Override
    public Optional<MemberCoupon> findById(MemberCouponId memberCouponId) {
        return memberCouponJpaRepository.findById(memberCouponId.getValue())
                .map(memberCouponPersistenceMapper::memberCouponEntityToMemberCoupon);
    }
}
