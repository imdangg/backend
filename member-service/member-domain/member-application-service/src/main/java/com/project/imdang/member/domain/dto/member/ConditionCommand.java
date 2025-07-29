package com.project.imdang.member.domain.dto.member;

import com.project.imdang.common.domain.valueobject.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConditionCommand {
    private MemberId memberId;
    private Purpose purpose;
    private Budget budget;
    private MonthIncome monthIncome;
    private CommutingArea commutingArea;
    private InfraNew infra;
    private Environment environment;
}
