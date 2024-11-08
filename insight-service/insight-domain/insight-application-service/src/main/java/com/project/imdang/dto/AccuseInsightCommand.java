package com.project.imdang.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class AccuseInsightCommand {

    // insightId - accuseMemberId UNIQUE
    private final String insightId;

    // accusedBy
    // 신고한 memberId
    private final String accuseMemberId;
}
