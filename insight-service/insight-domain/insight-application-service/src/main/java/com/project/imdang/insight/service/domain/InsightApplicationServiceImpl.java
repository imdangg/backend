package com.project.imdang.insight.service.domain;

import com.project.imdang.insight.service.domain.dto.AccuseInsightCommand;
import com.project.imdang.insight.service.domain.dto.DeleteInsightCommand;
import com.project.imdang.insight.service.domain.dto.InsightDetailRequest;
import com.project.imdang.insight.service.domain.dto.InsightDetailResponse;
import com.project.imdang.insight.service.domain.dto.InsightListRequest;
import com.project.imdang.insight.service.domain.dto.InsightListResponse;
import com.project.imdang.insight.service.domain.dto.InsightPreviewRequest;
import com.project.imdang.insight.service.domain.dto.InsightPreviewResponse;
import com.project.imdang.insight.service.domain.handler.AccuseInsightCommandHandler;
import com.project.imdang.insight.service.domain.ports.input.service.InsightApplicationService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InsightApplicationServiceImpl implements InsightApplicationService {

    private final AccuseInsightCommandHandler accuseInsightCommandHandler;

    @Override
    public InsightListResponse list(InsightListRequest insightListRequest) {
        return null;
    }

    @Override
    public InsightPreviewResponse preview(InsightPreviewRequest insightPreviewRequest) {
        return null;
    }

    @Override
    public InsightDetailResponse detail(InsightDetailRequest insightDetailRequest) {
        return null;
    }

    @Override
    public void validateAndEvaluateInsight() {
        // TODO - CHECK : 세션에 캐싱
    }

    @Override
    public void createInsight() {

    }

    @Override
    public void updateInsight() {

    }

    @Override
    public void deleteInsight(DeleteInsightCommand deleteInsightCommand) {

    }

    @Override
    public void accuseInsight(AccuseInsightCommand accuseInsightCommand) {
        accuseInsightCommandHandler.accuse(accuseInsightCommand);
    }
}
