package com.project.imdang.member.domain.dto.member;

import com.project.imdang.common.domain.valueobject.*;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConditionCommand {
    private MemberId memberId;
    private Purpose purpose;
    private Budget budget;
    private MonthIncome monthIncome;
    private CommutingArea commutingArea;
    private InfraNew infra;
    private Environment environment;
}
