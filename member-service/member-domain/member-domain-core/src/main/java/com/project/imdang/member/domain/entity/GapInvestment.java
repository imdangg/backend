
package com.project.imdang.member.domain.entity;

import com.project.imdang.common.domain.entity.AggregateRoot;
import com.project.imdang.common.domain.valueobject.CommutingArea;
import com.project.imdang.common.domain.valueobject.Environment;
import com.project.imdang.common.domain.valueobject.InfraNew;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.common.domain.valueobject.gapInvestment.*;
import com.project.imdang.common.domain.valueobject.living.ChildrenPlan;
import com.project.imdang.common.domain.valueobject.living.LivingPerson;
import com.project.imdang.common.domain.valueobject.living.SchoolDistrict;
import com.project.imdang.common.domain.valueobject.living.Traffic;
import com.project.imdang.member.domain.valueobject.GapInvestmentId;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GapInvestment extends AggregateRoot<GapInvestmentId> {

    private final MemberId memberId;
    private HopeGap hopeGap;
    private InvestmentPlan investmentPlan;
    private ApartmentSquare apartmentSquare;
    private Household household;
    private HouseType houseType;
    private CommutingArea commutingArea;
    private InfraNew infra;
    private Environment environment;

    @Builder
    public GapInvestment(GapInvestmentId id, MemberId memberId, HopeGap hopeGap, InvestmentPlan investmentPlan, ApartmentSquare apartmentSquare, Household household, HouseType houseType, CommutingArea commutingArea, InfraNew infra, Environment environment) {
        setId(id);
        this.memberId = memberId;
        this.hopeGap = hopeGap;
        this.investmentPlan = investmentPlan;
        this.apartmentSquare = apartmentSquare;
        this.household = household;
        this.houseType = houseType;
        this.commutingArea = commutingArea;
        this.infra = infra;
        this.environment = environment;
    }

    public static GapInvestment createNewGapInvestment(MemberId memberId, HopeGap hopeGap, InvestmentPlan investmentPlan, ApartmentSquare apartmentSquare, Household household, HouseType houseType, CommutingArea commutingArea, InfraNew infra, Environment environment) {
        return GapInvestment.builder()
                .memberId(memberId)
                .hopeGap(hopeGap)
                .investmentPlan(investmentPlan)
                .apartmentSquare(apartmentSquare)
                .household(household)
                .houseType(houseType)
                .commutingArea(commutingArea)
                .infra(infra)
                .environment(environment)
                .build();
    }
}