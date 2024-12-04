package com.project.imdang.insight.service.domain.mapper;

import com.project.imdang.insight.service.domain.dto.insight.accuse.AccuseInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.detail.DetailInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.evaluate.ValidateAndEvaluateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.evaluate.ValidateAndEvaluateInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.preview.PreviewInsightResponse;
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
        return CreateInsightResponse.builder()
                .insightId(insight.getId().getValue())
                .build();
    }

    public UpdateInsightResponse insightToUpdateInsightResponse(Insight insight) {
        return UpdateInsightResponse.builder()
                .insightId(insight.getId().getValue())
                .build();
    }

    public DeleteInsightResponse insightToDeleteInsightResponse(Insight insight) {
        return DeleteInsightResponse.builder()
                .insightId(insight.getId().getValue())
                .build();
    }

    public AccuseInsightResponse insightToAccuseInsightResponse(Insight insight) {
        return AccuseInsightResponse.builder()
                .insightId(insight.getId().getValue())
                .build();
    }

    public RecommendInsightResponse insightToRecommendInsightResponse(Insight insight) {
        return RecommendInsightResponse.builder()
                .insightId(insight.getId().getValue())
                .build();
    }

    public Insight validateAndEvaluateInsightCommandToInsight(ValidateAndEvaluateInsightCommand validateAndEvaluateInsightCommand) {
        return Insight.builder()

                .build();
    }

    public ValidateAndEvaluateInsightResponse insightToValidateAndEvaluateInsightCommand(Insight insight) {
        return ValidateAndEvaluateInsightResponse.builder()
                .insightId(insight.getId().getValue())
                .score(insight.getScore())
                .build();
    }

    public DetailInsightResponse insightToDetailInsightResponse(Insight insight) {
        return DetailInsightResponse.builder()
                .memberId(insight.getMemberId().getValue())
                .insightId(insight.getId().getValue())
                .recommendedCount(insight.getRecommendedCount())
                .address(insight.getAddress())
                .title(insight.getTitle())
                // TODO - CHECK
//                .mainImage(null)

//                .member(member)
                .createdAt(insight.getCreatedAt())
                .score(insight.getScore())
                .build();
    }

    public PreviewInsightResponse insightToPreviewInsightResponse(Insight insight) {
//        MemberResponse member = MemberResponse.builder()
//                .nickname()
//                .image()
//                .build();
        return PreviewInsightResponse.builder()
                .insightId(insight.getId().getValue())
                .recommendedCount(insight.getRecommendedCount())
                .address(insight.getAddress())
                .title(insight.getTitle())
                // TODO - CHECK
                .mainImage(null)
                .memberId(insight.getMemberId().getValue())
//                .member(member)
                .createdAt(insight.getCreatedAt())
                .score(insight.getScore())
                .build();
    }
}
