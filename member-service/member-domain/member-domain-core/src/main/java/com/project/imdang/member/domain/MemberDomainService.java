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

public interface MemberDomainService {

    Member join(Member member, String nickname, String birthDate, Gender gender, String deviceToken);

    Member createMember(String id, OAuthProvider oAuthProvider);
    Member accuseMember(Member member, AccusePenaltyPolicy accusePenaltyPolicy);

    Member logout(Member member);

    Member withdraw(Member member);

    Member storeRefreshToken(Member member, String refreshToken);
    Member updateMember(Member member);

    Member setCommonCondition(Member member,Purpose purpose, Budget budget, MonthIncome monthIncome);

    ActualLiving setActualLivingCondition(MemberId memberId, LivingPerson livingPerson, ChildrenPlan childrenPlan, SchoolDistrict schoolDistrict, Traffic traffic, CommutingArea commutingArea, InfraNew infra, Environment environment);

    GapInvestment setGapInvestmentCondition(MemberId memberId, HopeGap hopeGap, InvestmentPlan investmentPlan, ApartmentSquare apartmentSquare, Household household, HouseType houseType, CommutingArea commutingArea, InfraNew infra, Environment environment);
}
