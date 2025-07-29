package com.project.imdang.member.domain.dto.member;

import com.project.imdang.common.domain.valueobject.*;
import com.project.imdang.common.domain.valueobject.gapInvestment.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GapInvestmentConditionCommand extends ConditionCommand {
    private HopeGap hopeGap;
    private InvestmentPlan investmentPlan;
    private ApartmentSquare apartmentSquare;
    private Household household;
    private HouseType houseType;

    public GapInvestmentConditionCommand(MemberId memberId, Purpose purpose, Budget budget, MonthIncome monthIncome, CommutingArea commutingArea, InfraNew infra, Environment environment, HopeGap hopeGap, InvestmentPlan investmentPlan, ApartmentSquare apartmentSquare, Household household, HouseType houseType) {
        super(memberId, purpose, budget, monthIncome, commutingArea, infra, environment);
        this.hopeGap = hopeGap;
        this.investmentPlan = investmentPlan;
        this.apartmentSquare = apartmentSquare;
        this.household = household;
        this.houseType = houseType;
    }
}
