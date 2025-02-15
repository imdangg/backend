package com.project.imdang.member.service.domain.handler.coupon;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.MemberCouponDomainService;
import com.project.imdang.member.service.domain.dto.coupon.IssueMemberCouponCommand;
import com.project.imdang.member.service.domain.entity.Coupon;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.entity.MemberCoupon;
import com.project.imdang.member.service.domain.exception.CouponNotFoundException;
import com.project.imdang.member.service.domain.exception.MemberCouponDomainException;
import com.project.imdang.member.service.domain.handler.MemberHelper;
import com.project.imdang.member.service.domain.handler.coupon.policy.CouponPolicy;
import com.project.imdang.member.service.domain.mapper.MemberCouponDataMapper;
import com.project.imdang.member.service.domain.ports.output.CouponRepository;
import com.project.imdang.member.service.domain.ports.output.MemberCouponRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class IssueMemberCouponCommandHandler {
    private final MemberCouponDataMapper memberCouponDataMapper;
    private final MemberCouponRepository memberCouponRepository;
    private final CouponRepository couponRepository;
    private final MemberHelper memberHelper;
    private final MemberCouponDomainService memberCouponDomainService;
    private final Map<String, CouponPolicy> couponPolicies;

    public IssueMemberCouponCommandHandler(MemberCouponDataMapper memberCouponDataMapper,
                                           MemberCouponRepository memberCouponRepository,
                                           CouponRepository couponRepository,
                                           MemberHelper memberHelper,
                                           MemberCouponDomainService memberCouponDomainService,
                                           List<CouponPolicy> couponPolicies) {
        this.memberCouponDataMapper = memberCouponDataMapper;
        this.memberCouponRepository = memberCouponRepository;
        this.couponRepository = couponRepository;
        this.memberHelper = memberHelper;
        this.memberCouponDomainService = memberCouponDomainService;
        this.couponPolicies = couponPolicies.stream()
                .collect(Collectors.toMap(couponPolicy ->
                                couponPolicy.getClass().getAnnotation(Component.class).value(),
                        Function.identity()));
    }

    @Transactional
    public void issue(IssueMemberCouponCommand issueMemberCouponCommand) {
        // 1. 쿠폰 종류 확인
        Coupon coupon = checkCoupon(issueMemberCouponCommand.getName());
        // 2. 사용자 확인
        MemberId memberId = new MemberId(issueMemberCouponCommand.getMemberId());
        Member member = memberHelper.get(memberId);
        // 2-1. 웰컴 쿠폰 받기 저장
        if (issueMemberCouponCommand.getName().equals("Welcome")) {
            member.updateCouponReceivedStatus();
            memberHelper.save(member);
        }
        // 3. 쿠폰 정책 적용
        CouponPolicy couponPolicy = couponPolicies.get(issueMemberCouponCommand.getName());
        Integer couponQuantity = couponPolicy.apply(coupon, member);
        // 4. 쿠폰 발급
        List<MemberCoupon> memberCoupons = memberCouponDataMapper.issueMemberCouponCommandToMemberCoupons(member, coupon, couponQuantity);
        memberCoupons.forEach(memberCouponDomainService::issue);
        // 5. 쿠폰 저장
        save(memberCoupons);
        log.info("MemberCoupons are issued by Member[id: {}].", member.getId().getValue());
    }

    private List<MemberCoupon> save(List<MemberCoupon> memberCoupons) {
        List<MemberCoupon> saved = memberCouponRepository.saveAll(memberCoupons);
        if (saved.isEmpty()) {
            String errorMessage = "Could not save memberCoupons!";
            log.error(errorMessage);
            throw new MemberCouponDomainException(errorMessage);
        }
        saved.forEach(coupon -> log.info("MemberCoupon[id: {}] is saved.", coupon.getId().getValue()));
        return saved;
    }

    private Coupon checkCoupon(String couponName) {
        return couponRepository.findByName(couponName)
                .orElseThrow(() -> new CouponNotFoundException(String.format("Could not find Coupon [name : %s]", couponName)));
    }
}
