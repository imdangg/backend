package com.project.imdang.member.persistence.mapper;


import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.entity.ActualLiving;
import com.project.imdang.member.domain.valueobject.ActualLivingId;
import com.project.imdang.member.persistence.entity.ActualLivingEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ActualLivingPersistenceMapper {
    public ActualLivingEntity actualLivingToActualLivingEntity(ActualLiving actualLiving) {
        return ActualLivingEntity.builder()
                .id(!Objects.isNull(actualLiving.getId()) ? actualLiving.getId().getValue() : null )
                .memberId(actualLiving.getMemberId().getValue())
                .livingPerson(actualLiving.getLivingPerson())
                .childrenPlan(actualLiving.getChildrenPlan())
                .commutingArea(actualLiving.getCommutingArea())
                .traffic(actualLiving.getTraffic())
                .schoolDistrict(actualLiving.getSchoolDistrict())
                .infra(actualLiving.getInfra())
                .environment(actualLiving.getEnvironment())
                .build();
    }

    public ActualLiving actualLivingEntityToActualLiving(ActualLivingEntity actualLivingEntity) {
        return ActualLiving.builder()
                .id(new ActualLivingId(actualLivingEntity.getId()))
                .memberId(new MemberId(actualLivingEntity.getMemberId()))
                .livingPerson(actualLivingEntity.getLivingPerson())
                .childrenPlan(actualLivingEntity.getChildrenPlan())
                .commutingArea(actualLivingEntity.getCommutingArea())
                .traffic(actualLivingEntity.getTraffic())
                .schoolDistrict(actualLivingEntity.getSchoolDistrict())
                .infra(actualLivingEntity.getInfra())
                .environment(actualLivingEntity.getEnvironment())
                .build();
    }
}
