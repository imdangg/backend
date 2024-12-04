package com.project.imdang.insight.service.domain.ports.input.service;

import com.project.imdang.insight.service.domain.dto.insight.accuse.AccuseInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.accuse.AccuseInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.detail.DetailInsightQuery;
import com.project.imdang.insight.service.domain.dto.insight.detail.DetailInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.evaluate.ValidateAndEvaluateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.evaluate.ValidateAndEvaluateInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.ListInsightByAddressQuery;
import com.project.imdang.insight.service.domain.dto.insight.list.ListInsightByApartmentComplexQuery;
import com.project.imdang.insight.service.domain.dto.insight.list.ListInsightQuery;
import com.project.imdang.insight.service.domain.dto.insight.preview.PreviewInsightQuery;
import com.project.imdang.insight.service.domain.dto.insight.preview.PreviewInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.request.RequestInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.request.RequestInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.update.UpdateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.update.UpdateInsightResponse;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;

import java.util.List;
import java.util.Map;

public interface InsightApplicationService {
    List<InsightResponse> listInsight(ListInsightQuery listInsightQuery);
    Map<ApartmentComplex, List<InsightResponse>> listInsightByAddress(ListInsightByAddressQuery listInsightByAddressQuery);
    List<InsightResponse> listInsightByApartmentComplex(ListInsightByApartmentComplexQuery listInsightByApartmentComplexQuery);
    Map<ApartmentComplex, List<InsightResponse>> listInsightByMyVisitedApartmentComplex(ListInsightQuery listInsightQuery);

    PreviewInsightResponse previewInsight(PreviewInsightQuery previewInsightQuery);
    DetailInsightResponse detailInsight(DetailInsightQuery detailInsightQuery);

    ValidateAndEvaluateInsightResponse validateAndEvaluateInsight(ValidateAndEvaluateInsightCommand validateAndEvaluateInsightCommand);
    // = uploadInsight
    CreateInsightResponse createInsight(CreateInsightCommand createInsightCommand);
    UpdateInsightResponse updateInsight(UpdateInsightCommand updateInsightCommand);
    DeleteInsightResponse deleteInsight(DeleteInsightCommand deleteInsightCommand);

    RecommendInsightResponse recommendInsight(RecommendInsightCommand recommendInsightCommand);
    AccuseInsightResponse accuseInsight(AccuseInsightCommand accuseInsightCommand);
    RequestInsightResponse requestInsight(RequestInsightCommand requestInsightCommand);
}
