package com.project.imdang.insight.service.domain.mapper;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.dto.insight.accuse.AccuseInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.detail.DetailInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.evaluate.ValidateAndEvaluateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.evaluate.ValidateAndEvaluateInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.update.UpdateInsightResponse;
import com.project.imdang.insight.service.domain.entity.Insight;
import org.springframework.stereotype.Component;

@Component
public class InsightDataMapper {

    public InsightResponse insightToInsightResponse(Insight insight) {
        return InsightResponse.builder()
                .insightId(insight.getId().getValue())
                .recommendedCount(insight.getRecommendedCount())
                .address(insight.getAddress())
                .title(insight.getTitle())
                .mainImage(insight.getMainImage())
                .memberId(insight.getMemberId().getValue())
                .build();
    }

    public Insight createInsightCommandToInsight(CreateInsightCommand createInsightCommand) {
        return Insight.builder()
                .memberId(new MemberId(createInsightCommand.getMemberId()))
                .score(createInsightCommand.getScore())
                .address(createInsightCommand.getAddress())
                .apartmentComplex(createInsightCommand.getApartmentComplex())
                .title(createInsightCommand.getTitle())
                .contents(createInsightCommand.getContents())
                .mainImage(createInsightCommand.getMainImage())
                .summary(createInsightCommand.getSummary())
                .visitAt(createInsightCommand.getVisitAt())
                .visitMethod(createInsightCommand.getVisitMethod())
                .access(createInsightCommand.getAccess())
                .infra(createInsightCommand.getInfra())
                .complexEnvironment(createInsightCommand.getComplexEnvironment())
                .complexFacility(createInsightCommand.getComplexFacility())
                .favorableNews(createInsightCommand.getFavorableNews())
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
                .address(validateAndEvaluateInsightCommand.getAddress())
                .apartmentComplex(validateAndEvaluateInsightCommand.getApartmentComplex())
                .title(validateAndEvaluateInsightCommand.getTitle())
                .contents(validateAndEvaluateInsightCommand.getContents())
                .mainImage(validateAndEvaluateInsightCommand.getMainImage())
                .summary(validateAndEvaluateInsightCommand.getSummary())
                .visitAt(validateAndEvaluateInsightCommand.getVisitAt())
                .visitMethod(validateAndEvaluateInsightCommand.getVisitMethod())
                .access(validateAndEvaluateInsightCommand.getAccess())
                .infra(validateAndEvaluateInsightCommand.getInfra())
                .complexEnvironment(validateAndEvaluateInsightCommand.getComplexEnvironment())
                .complexFacility(validateAndEvaluateInsightCommand.getComplexFacility())
                .favorableNews(validateAndEvaluateInsightCommand.getFavorableNews())
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
                .address(insight.getAddress())
                .apartmentComplex(insight.getApartmentComplex())
                .title(insight.getTitle())
                .contents(insight.getContents())
                .mainImage(insight.getMainImage())
                .summary(insight.getSummary())
                .visitAt(insight.getVisitAt())
                .visitMethod(insight.getVisitMethod())
                .access(insight.getAccess())
                .infra(insight.getInfra())
                .complexEnvironment(insight.getComplexEnvironment())
                .complexFacility(insight.getComplexFacility())
                .favorableNews(insight.getFavorableNews())
                .recommendedCount(insight.getRecommendedCount())
                .accusedCount(insight.getAccusedCount())
                .viewCount(insight.getViewCount())
                .score(insight.getScore())
                .createdAt(insight.getCreatedAt())
                .build();
    }
}
