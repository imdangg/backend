package com.project.imdang.member.persistence.mapper;


import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.entity.GapInvestment;
import com.project.imdang.member.domain.valueobject.GapInvestmentId;
import com.project.imdang.member.persistence.entity.GapInvestmentEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GapInvestmentPersistenceMapper {
    public GapInvestmentEntity gapInvestmentToGapInvestmentEntity(GapInvestment gapInvestment) {
        return GapInvestmentEntity.builder()
                .id(!Objects.isNull(gapInvestment.getId()) ? gapInvestment.getId().getValue() : null )
                .memberId(gapInvestment.getMemberId().getValue())
                .hopeGap(gapInvestment.getHopeGap())
                .investmentPlan(gapInvestment.getInvestmentPlan())
                .apartmentSquare(gapInvestment.getApartmentSquare())
                .household(gapInvestment.getHousehold())
                .commutingArea(gapInvestment.getCommutingArea())
                .infra(gapInvestment.getInfra())
                .environment(gapInvestment.getEnvironment())
                .build();
    }

    public GapInvestment gapInvestmentEntityToGapInvestment(GapInvestmentEntity gapInvestmentEntity) {
        return GapInvestment.builder()
                .id(new GapInvestmentId(gapInvestmentEntity.getId()))
                .memberId(new MemberId(gapInvestmentEntity.getMemberId()))
                .hopeGap(gapInvestmentEntity.getHopeGap())
                .investmentPlan(gapInvestmentEntity.getInvestmentPlan())
                .apartmentSquare(gapInvestmentEntity.getApartmentSquare())
                .household(gapInvestmentEntity.getHousehold())
                .commutingArea(gapInvestmentEntity.getCommutingArea())
                .infra(gapInvestmentEntity.getInfra())
                .environment(gapInvestmentEntity.getEnvironment())
                .build();
    }
}
