package com.project.imdang.insight.service.domain.mapper;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.dto.insight.accuse.AccuseInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.detail.DetailInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.update.UpdateInsightResponse;
import com.project.imdang.insight.service.domain.entity.ExchangeRequest;
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
                .title(createInsightCommand.getTitle())
                .address(createInsightCommand.getAddress())
                .apartmentComplex(createInsightCommand.getApartmentComplex())
                .visitAt(createInsightCommand.getVisitAt())
                .visitTimes(createInsightCommand.getVisitTimes())
                .visitMethods(createInsightCommand.getVisitMethods())
                .access(createInsightCommand.getAccess())
                .summary(createInsightCommand.getSummary())
                .infra(createInsightCommand.getInfra())
                .complexEnvironment(createInsightCommand.getComplexEnvironment())
                .complexFacility(createInsightCommand.getComplexFacility())
                .favorableNews(createInsightCommand.getFavorableNews())
                .score(createInsightCommand.getScore())
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

    public DetailInsightResponse insightToDetailInsightResponse(Insight insight, ExchangeRequest exchangeRequest) {
        return DetailInsightResponse.builder()
                .memberId(insight.getMemberId().getValue())
                .insightId(insight.getId().getValue())
                .mainImage(insight.getMainImage())
                .title(insight.getTitle())
                .address(insight.getAddress())
                .apartmentComplex(insight.getApartmentComplex())
                .visitAt(insight.getVisitAt())
                .visitTimes(insight.getVisitTimes())
                .visitMethods(insight.getVisitMethods())
                .access(insight.getAccess())
                .summary(insight.getSummary())
                .infra(insight.getInfra())
                .complexEnvironment(insight.getComplexEnvironment())
                .complexFacility(insight.getComplexFacility())
                .favorableNews(insight.getFavorableNews())
                .recommendedCount(insight.getRecommendedCount())
                .accusedCount(insight.getAccusedCount())
                .viewCount(insight.getViewCount())
                .score(insight.getScore())
                .createdAt(insight.getCreatedAt())
                // TODO - CHECK
                .exchangeRequestStatus(exchangeRequest != null ? exchangeRequest.getStatus() : null)
                .exchangeRequestId(exchangeRequest != null ? exchangeRequest.getId().getValue() : null)
                .build();
    }
}
