package com.project.imdang.member.persistence.entity;

import com.project.imdang.common.domain.valueobject.CommutingArea;
import com.project.imdang.common.domain.valueobject.Environment;
import com.project.imdang.common.domain.valueobject.InfraNew;
import com.project.imdang.common.domain.valueobject.living.ChildrenPlan;
import com.project.imdang.common.domain.valueobject.living.LivingPerson;
import com.project.imdang.common.domain.valueobject.living.SchoolDistrict;
import com.project.imdang.common.domain.valueobject.living.Traffic;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "actual_living")
@Entity
public class ActualLivingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "CHAR(36)")
    private UUID memberId;

    @Enumerated(EnumType.STRING)
    private LivingPerson livingPerson;
    @Enumerated(EnumType.STRING)
    private ChildrenPlan childrenPlan;
    @Enumerated(EnumType.STRING)
    private CommutingArea commutingArea;
    @Enumerated(EnumType.STRING)
    private Traffic traffic;
    @Enumerated(EnumType.STRING)
    private SchoolDistrict schoolDistrict;
    @Enumerated(EnumType.STRING)
    private InfraNew infra;
    @Enumerated(EnumType.STRING)
    private Environment environment;
}
