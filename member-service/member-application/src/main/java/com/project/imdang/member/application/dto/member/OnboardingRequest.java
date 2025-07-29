package com.project.imdang.member.application.dto.member;

import com.project.imdang.common.domain.valueobject.*;
import com.project.imdang.common.domain.valueobject.gapInvestment.*;
import com.project.imdang.common.domain.valueobject.living.ChildrenPlan;
import com.project.imdang.common.domain.valueobject.living.LivingPerson;
import com.project.imdang.common.domain.valueobject.living.SchoolDistrict;
import com.project.imdang.common.domain.valueobject.living.Traffic;

public record OnboardingRequest(
        Purpose purpose,
        Budget budget,
        MonthIncome monthIncome,
        LivingPerson livingPerson,
        ChildrenPlan childrenPlan,
        HopeGap hopeGap,
        InvestmentPlan investmentPlan,
        Traffic traffic,
        SchoolDistrict schoolDistrict,
        ApartmentSquare apartmentSquare,
        Household household,
        HouseType houseType,
        CommutingArea commutingArea,
        InfraNew infra,
        Environment environment,
        String firstPriority,
        String secondPriority,
        String thirdPriority,
        String interestDistrict
) {
}
