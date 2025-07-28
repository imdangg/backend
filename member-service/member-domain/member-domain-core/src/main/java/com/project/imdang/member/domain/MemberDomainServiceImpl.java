package com.project.imdang.member.domain;

import com.project.imdang.common.domain.valueobject.*;
import com.project.imdang.common.domain.valueobject.gapInvestment.*;
import com.project.imdang.common.domain.valueobject.living.ChildrenPlan;
import com.project.imdang.common.domain.valueobject.living.LivingPerson;
import com.project.imdang.common.domain.valueobject.living.SchoolDistrict;
import com.project.imdang.common.domain.valueobject.living.Traffic;
import com.project.imdang.member.domain.entity.ActualLiving;
import com.project.imdang.member.domain.entity.GapInvestment;
import com.project.imdang.member.domain.entity.Member;
import com.project.imdang.member.domain.valueobject.AccusePenaltyPolicy;

public class MemberDomainServiceImpl implements MemberDomainService {

    @Override
    public Member join(Member member, String nickname, String birthDate, Gender gender, String deviceToken) {
        member.join(nickname, birthDate, gender, deviceToken);
        return member;
    }

    @Override
    public Member createMember(String oAuthId, OAuthProvider oAuthProvider) {
        return Member.createNewMember(oAuthId, oAuthProvider);
    }

    @Override
    public Member accuseMember(Member member, AccusePenaltyPolicy accusePenaltyPolicy) {
        member.accuse(accusePenaltyPolicy);
        return member;
    }

    @Override
    public Member logout(Member member) {
        member.logout();
        return member;
    }

    @Override
    public Member withdraw(Member member) {
        member.withdraw();
        return member;
    }

    @Override
    public Member storeRefreshToken(Member member, String refreshToken) {
        member.storeRefreshToken(refreshToken);
        return member;
    }

    @Override
    public Member updateMember(Member member) {
        member.updateInsightCreateDate();
        return member;
    }

    @Override
    public Member setCommonCondition(Member member, Purpose purpose, Budget budget, MonthIncome monthIncome) {
        member.setCommonCondition(purpose, budget, monthIncome);
        return member;
    }

    @Override
    public ActualLiving setActualLivingCondition(MemberId memberId, LivingPerson livingPerson, ChildrenPlan childrenPlan, SchoolDistrict schoolDistrict, Traffic traffic, CommutingArea commutingArea, InfraNew infra, Environment environment) {
        return null;
    }

    @Override
    public GapInvestment setGapInvestmentCondition(MemberId memberId, HopeGap hopeGap, InvestmentPlan investmentPlan, ApartmentSquare apartmentSquare, Household household, HouseType houseType, CommutingArea commutingArea, InfraNew infra, Environment environment) {
        return null;
    }
}
