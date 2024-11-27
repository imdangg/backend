package com.project.imdang.insight.service.domain.mapper;

import com.project.imdang.insight.service.domain.dto.insight.accuse.AccuseInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.accuse.AccuseInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.detail.DetailInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.evaluate.ValidateAndEvaluateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.evaluate.ValidateAndEvaluateInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.preview.PreviewInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.update.UpdateInsightResponse;
import com.project.imdang.insight.service.domain.entity.Insight;
import org.springframework.stereotype.Component;

@Component
public class InsightDataMapper {

    public Insight createInsightCommandToInsight(CreateInsightCommand createInsightCommand) {
        return Insight.builder()

                .build();
    }

    public CreateInsightResponse insightToCreateInsightResponse(Insight insight) {
        return new CreateInsightResponse();
    }

    public UpdateInsightResponse insightToUpdateInsightResponse(Insight insight) {
        return new UpdateInsightResponse();
    }

    public Insight deleteInsightCommandToInsight(DeleteInsightCommand deleteInsightCommand) {
        return Insight.builder()

                .build();
    }

    public DeleteInsightResponse insightToDeleteInsightResponse(Insight insight) {
        return new DeleteInsightResponse();
    }

    public Insight accuseInsightCommandToInsight(AccuseInsightCommand accuseInsightCommand) {
        return Insight.builder().build();
    }

    public AccuseInsightResponse insightToAccuseInsightResponse(Insight insight) {
        return new AccuseInsightResponse();
    }

    public Insight recommendInsightCommandToInsight(RecommendInsightCommand recommendInsightCommand) {
        return Insight.builder().build();
    }

    public RecommendInsightResponse insightToRecommendInsightResponse(Insight insight) {
        return new RecommendInsightResponse();
    }

    public Insight validateAndEvaluateInsightCommandToInsight(ValidateAndEvaluateInsightCommand validateAndEvaluateInsightCommand) {
        return Insight.builder().build();
    }

    public ValidateAndEvaluateInsightResponse insightToValidateAndEvaluateInsightCommand(Insight insight) {
        return new ValidateAndEvaluateInsightResponse();
    }

    public DetailInsightResponse insightToDetailInsightResponse(Insight insight) {
        return null;
    }

    public PreviewInsightResponse insightToPreviewInsightResponse(Insight insight) {
        return null;
    }
}
