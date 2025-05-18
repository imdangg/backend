package com.project.imdang.insight.domain.dto.insight.detail;

import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import lombok.Getter;

@Getter
public class DetailInsightQuery {
    private InsightId insightId;
    private MemberId memberId;  // requestedBy

    public DetailInsightQuery(InsightId insightId, MemberId memberId) {
        this.insightId = insightId;
        this.memberId = memberId;
    }
}
