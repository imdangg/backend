package com.project.imdang.insight.service.domain.dto.insight.accuse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class AccuseInsightCommand {

    // insightId - accuseMemberId UNIQUE
    private UUID insightId;
    // accusedBy
    // 신고한 memberId
//    private UUID accuseMemberId;
}
