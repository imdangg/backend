package com.project.imdang.insight.service.domain.ports.input.service;

import com.project.imdang.insight.service.domain.dto.AccuseInsightCommand;
import com.project.imdang.insight.service.domain.dto.DeleteInsightCommand;
import com.project.imdang.insight.service.domain.dto.InsightDetailRequest;
import com.project.imdang.insight.service.domain.dto.InsightDetailResponse;
import com.project.imdang.insight.service.domain.dto.InsightListRequest;
import com.project.imdang.insight.service.domain.dto.InsightListResponse;
import com.project.imdang.insight.service.domain.dto.InsightPreviewRequest;
import com.project.imdang.insight.service.domain.dto.InsightPreviewResponse;

public interface InsightApplicationService {

    InsightListResponse list(InsightListRequest insightListRequest);
    InsightPreviewResponse preview(InsightPreviewRequest insightPreviewRequest);
    InsightDetailResponse detail(InsightDetailRequest insightDetailRequest);

    void validateAndEvaluateInsight();
    // = uploadInsight
    void createInsight();
    void updateInsight();
    void deleteInsight(DeleteInsightCommand deleteInsightCommand);

    void accuseInsight(AccuseInsightCommand accuseInsightCommand);
}
