package com.project.imdang.insight.domain.dto.insight.recommend;

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
public class RecommendInsightCommand {
    private InsightId insightId;
    private MemberId recommendMemberId;  // recommendedBy
}
