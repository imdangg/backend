package com.project.imdang.ports.input.service;

import com.project.imdang.dto.AccuseInsightCommand;
import com.project.imdang.dto.DeleteInsightCommand;

public interface InsightCommandApplicationService {

    void validateAndEvaluateInsight();
    // = uploadInsight
    void createInsight();
    void updateInsight();
    void deleteInsight(DeleteInsightCommand deleteInsightCommand);

    void accuseInsight(AccuseInsightCommand accuseInsightCommand);
}
