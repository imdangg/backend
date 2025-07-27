
package com.project.imdang.member.domain.entity;

import com.project.imdang.common.domain.entity.AggregateRoot;
import com.project.imdang.common.domain.valueobject.CommutingArea;
import com.project.imdang.common.domain.valueobject.Environment;
import com.project.imdang.common.domain.valueobject.InfraNew;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.common.domain.valueobject.living.ChildrenPlan;
import com.project.imdang.common.domain.valueobject.living.LivingPerson;
import com.project.imdang.common.domain.valueobject.living.SchoolDistrict;
import com.project.imdang.common.domain.valueobject.living.Traffic;
import com.project.imdang.member.domain.valueobject.ActualLivingId;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ActualLiving extends AggregateRoot<ActualLivingId> {

    private final MemberId memberId;
    private LivingPerson livingPerson;
    private ChildrenPlan childrenPlan;
    private CommutingArea commutingArea;
    private Traffic traffic;
    private SchoolDistrict schoolDistrict;
    private InfraNew infra;
    private Environment environment;

    @Builder
    public ActualLiving(MemberId memberId, LivingPerson livingPerson, ChildrenPlan childrenPlan, CommutingArea commutingArea, Traffic traffic, SchoolDistrict schoolDistrict, InfraNew infra, Environment environment) {
        this.memberId = memberId;
        this.livingPerson = livingPerson;
        this.childrenPlan = childrenPlan;
        this.commutingArea = commutingArea;
        this.traffic = traffic;
        this.schoolDistrict = schoolDistrict;
        this.infra = infra;
        this.environment = environment;
    }
}
