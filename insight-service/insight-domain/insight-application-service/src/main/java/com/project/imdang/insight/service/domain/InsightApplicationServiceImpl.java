package com.project.imdang.insight.service.domain;

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
import com.project.imdang.insight.service.domain.handler.insight.AccuseInsightCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.CreateInsightCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.DeleteInsightCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.RecommendInsightCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.UpdateInsightCommandHandler;
import com.project.imdang.insight.service.domain.ports.input.service.InsightApplicationService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InsightApplicationServiceImpl implements InsightApplicationService {

    private final CreateInsightCommandHandler createInsightCommandHandler;
    private final UpdateInsightCommandHandler updateInsightCommandHandler;
    private final DeleteInsightCommandHandler deleteInsightCommandHandler;

    private final RecommendInsightCommandHandler RecommendInsightCommandHandler;
    private final AccuseInsightCommandHandler accuseInsightCommandHandler;

    @Override
    public InsightListResponse list(InsightListQuery insightListQuery) {
        return null;
    }

    @Override
    public InsightPreviewResponse preview(InsightPreviewQuery insightPreviewQuery) {
        return null;
    }

    @Override
    public InsightDetailResponse detail(InsightDetailQuery insightDetailQuery) {
        return null;
    }

    @Override
    public void validateAndEvaluateInsight(ValidateAndEvaluateInsightCommand validateAndEvaluateInsightCommand) {
        // TODO : 캐싱
    }

    @Override
    public void createInsight(CreateInsightCommand createInsightCommand) {

    }

    @Override
    public void updateInsight(UpdateInsightCommand updateInsightCommand) {

    }

    @Override
    public void deleteInsight(DeleteInsightCommand deleteInsightCommand) {

    }

    @Override
    public void recommendInsight(RecommendInsightCommand recommendInsightCommand) {

    }

    @Override
    public void accuseInsight(AccuseInsightCommand accuseInsightCommand) {
        accuseInsightCommandHandler.accuse(accuseInsightCommand);
    }
}
