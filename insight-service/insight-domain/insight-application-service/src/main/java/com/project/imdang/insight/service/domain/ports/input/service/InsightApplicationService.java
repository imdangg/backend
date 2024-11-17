package com.project.imdang.insight.service.domain.ports.input.service;

import com.project.imdang.insight.service.domain.dto.insight.accuse.AccuseInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.detail.InsightDetailQuery;
import com.project.imdang.insight.service.domain.dto.insight.detail.InsightDetailResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightListQuery;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightListResponse;
import com.project.imdang.insight.service.domain.dto.insight.preview.InsightPreviewQuery;
import com.project.imdang.insight.service.domain.dto.insight.preview.InsightPreviewResponse;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.update.UpdateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.evaluate.ValidateAndEvaluateInsightCommand;

public interface InsightApplicationService {

    InsightListResponse list(InsightListQuery insightListQuery);
    InsightPreviewResponse preview(InsightPreviewQuery insightPreviewQuery);
    InsightDetailResponse detail(InsightDetailQuery insightDetailQuery);

    void validateAndEvaluateInsight(ValidateAndEvaluateInsightCommand validateAndEvaluateInsightCommand);

    // = uploadInsight
    void createInsight(CreateInsightCommand createInsightCommand);
    void updateInsight(UpdateInsightCommand updateInsightCommand);
    void deleteInsight(DeleteInsightCommand deleteInsightCommand);

    void recommendInsight(RecommendInsightCommand recommendInsightCommand);
    void accuseInsight(AccuseInsightCommand accuseInsightCommand);
}
