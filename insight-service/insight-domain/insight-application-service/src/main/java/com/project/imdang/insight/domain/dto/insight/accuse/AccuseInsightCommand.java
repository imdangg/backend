package com.project.imdang.insight.domain.dto.insight.accuse;

import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccuseInsightCommand {
    // insightId - accuseMemberId UNIQUE
    private InsightId insightId;
    private MemberId accuseMemberId;    // accusedBy : 신고한 memberId
}
