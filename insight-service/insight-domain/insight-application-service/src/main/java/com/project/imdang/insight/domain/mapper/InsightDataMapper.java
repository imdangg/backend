package com.project.imdang.insight.domain.mapper;

import com.project.imdang.insight.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.domain.dto.insight.detail.InsightDetailResult;
import com.project.imdang.insight.domain.dto.insight.list.InsightResult;
import com.project.imdang.insight.domain.entity.Insight;
import com.project.imdang.insight.domain.entity.InsightImage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InsightDataMapper {

    public InsightResult insightToInsightResponse(Insight insight, String memberNickname) {
        return InsightResult.builder()
                .insightId(insight.getId())
                .recommendedCount(insight.getRecommendedCount())
                .address(insight.getAddress())
                .title(insight.getTitle())
                .memberNickname(memberNickname)
                .createdAt(insight.getCreatedAt())
                .build();
    }

    public Insight createInsightCommandToInsight(CreateInsightCommand createInsightCommand) {
        return Insight.builder()
                .memberId(createInsightCommand.getMemberId())
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
                .build();
    }

    public InsightDetailResult insightToDetailInsightResponse(Insight insight,
                                                              String memberNickname,
                                                              Boolean recommended,
                                                              Boolean accused,
                                                              Boolean createdByMe) {
        return InsightDetailResult.builder()
                .memberId(insight.getMemberId())
                .memberNickname(memberNickname)
                .insightId(insight.getId())
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
                .recommended(recommended)
                .accused(accused)
                .recommendedCount(insight.getRecommendedCount())
                .accusedCount(insight.getAccusedCount())
                .viewCount(insight.getViewCount())
                .createdAt(insight.getCreatedAt())
                .createdByMe(createdByMe)
                .build();
    }
}
