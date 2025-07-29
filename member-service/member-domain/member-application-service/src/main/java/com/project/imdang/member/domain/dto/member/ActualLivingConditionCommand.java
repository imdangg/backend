package com.project.imdang.member.domain.dto.member;

import com.project.imdang.common.domain.valueobject.*;
import com.project.imdang.common.domain.valueobject.living.ChildrenPlan;
import com.project.imdang.common.domain.valueobject.living.LivingPerson;
import com.project.imdang.common.domain.valueobject.living.SchoolDistrict;
import com.project.imdang.common.domain.valueobject.living.Traffic;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ActualLivingConditionCommand extends ConditionCommand {
    private LivingPerson livingPerson;
    private ChildrenPlan childrenPlan;
    private Traffic traffic;
    private SchoolDistrict schoolDistrict;

    public ActualLivingConditionCommand(MemberId memberId, Purpose purpose, Budget budget, MonthIncome monthIncome, CommutingArea commutingArea, InfraNew infra, Environment environment, LivingPerson livingPerson, ChildrenPlan childrenPlan, Traffic traffic, SchoolDistrict schoolDistrict) {
        super(memberId, purpose, budget, monthIncome, commutingArea, infra, environment);
        this.livingPerson = livingPerson;
        this.childrenPlan = childrenPlan;
        this.traffic = traffic;
        this.schoolDistrict = schoolDistrict;
    }
}
