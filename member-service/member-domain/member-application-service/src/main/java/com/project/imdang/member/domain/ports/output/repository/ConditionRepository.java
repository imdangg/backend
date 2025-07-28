package com.project.imdang.member.domain.ports.output.repository;

import com.project.imdang.member.domain.entity.ActualLiving;
import com.project.imdang.member.domain.entity.GapInvestment;

public interface ConditionRepository {
    GapInvestment saveGapInvestment(GapInvestment gapInvestment);
    ActualLiving saveActualLiving(ActualLiving actualLiving);
}
