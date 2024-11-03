package com.project.imdang;

import com.project.imdang.dto.AccuseInsightCommand;
import com.project.imdang.dto.DeleteInsightCommand;
import com.project.imdang.handler.AccuseInsightCommandHandler;
import com.project.imdang.ports.input.service.InsightCommandApplicationService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InsightCommandApplicationServiceImpl implements InsightCommandApplicationService {

    private final AccuseInsightCommandHandler accuseInsightCommandHandler;

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
