package com.project.imdang.member.persistence.entity;

import com.project.imdang.common.domain.valueobject.CommutingArea;
import com.project.imdang.common.domain.valueobject.Environment;
import com.project.imdang.common.domain.valueobject.InfraNew;
import com.project.imdang.common.domain.valueobject.gapInvestment.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "gap_investment")
@Entity
public class GapInvestmentEntity {

    @Id
    private Long id;

    private UUID memberId;
    @Enumerated(EnumType.STRING)
    private HopeGap hopeGap;
    @Enumerated(EnumType.STRING)
    private InvestmentPlan investmentPlan;
    @Enumerated(EnumType.STRING)
    private ApartmentSquare apartmentSquare;
    @Enumerated(EnumType.STRING)
    private Household household;
    @Enumerated(EnumType.STRING)
    private HouseType houseType;
    @Enumerated(EnumType.STRING)
    private CommutingArea commutingArea;
    @Enumerated(EnumType.STRING)
    private InfraNew infra;
    @Enumerated(EnumType.STRING)
    private Environment environment;
}
