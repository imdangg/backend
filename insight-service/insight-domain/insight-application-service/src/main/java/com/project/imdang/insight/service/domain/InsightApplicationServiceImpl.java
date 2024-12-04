package com.project.imdang.insight.service.domain;

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
import com.project.imdang.insight.service.domain.handler.insight.AccuseInsightCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.CreateInsightCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.DeleteInsightCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.DetailInsightCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.ListInsightByAddressCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.ListInsightByApartmentComplexCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.ListInsightByMyVisitedApartmentComplexCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.ListInsightCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.PreviewInsightCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.RecommendInsightCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.UpdateInsightCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.ValidateAndEvaluateInsightCommandHandler;
import com.project.imdang.insight.service.domain.handler.request.RequestInsightCommandHandler;
import com.project.imdang.insight.service.domain.ports.input.service.InsightApplicationService;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Validated
@RequiredArgsConstructor
@Service
public class InsightApplicationServiceImpl implements InsightApplicationService {

    private final ListInsightCommandHandler listInsightCommandHandler;
    private final ListInsightByAddressCommandHandler listInsightByAddressCommandHandler;
    private final ListInsightByApartmentComplexCommandHandler listInsightByApartmentComplexCommandHandler;
    private final ListInsightByMyVisitedApartmentComplexCommandHandler listInsightByMyVisitedApartmentComplexCommandHandler;

    private final PreviewInsightCommandHandler previewInsightCommandHandler;
    private final DetailInsightCommandHandler detailInsightCommandHandler;

    private final ValidateAndEvaluateInsightCommandHandler validateAndEvaluateInsightCommandHandler;
    private final CreateInsightCommandHandler createInsightCommandHandler;
    private final UpdateInsightCommandHandler updateInsightCommandHandler;
    private final DeleteInsightCommandHandler deleteInsightCommandHandler;
    private final RequestInsightCommandHandler requestInsightCommandHandler;

    private final RecommendInsightCommandHandler recommendInsightCommandHandler;
    private final AccuseInsightCommandHandler accuseInsightCommandHandler;

    @Override
    public List<InsightResponse> listInsight(ListInsightQuery listInsightQuery) {
        return listInsightCommandHandler.listInsight(listInsightQuery);
    }

    @Override
    public Map<ApartmentComplex, List<InsightResponse>> listInsightByAddress(ListInsightByAddressQuery listInsightByAddressQuery) {
        return listInsightByAddressCommandHandler.listInsightByAddress(listInsightByAddressQuery);
    }

    @Override
    public List<InsightResponse> listInsightByApartmentComplex(ListInsightByApartmentComplexQuery listInsightByApartmentComplexQuery) {
        return listInsightByApartmentComplexCommandHandler.listInsightByApartmentComplex(listInsightByApartmentComplexQuery);
    }

    @Override
    public Map<ApartmentComplex, List<InsightResponse>> listInsightByMyVisitedApartmentComplex(ListInsightQuery listInsightQuery) {
        return listInsightByMyVisitedApartmentComplexCommandHandler.listInsightByMyVisitedApartmentComplex(listInsightQuery);
    }

    @Override
    public PreviewInsightResponse previewInsight(PreviewInsightQuery previewInsightQuery) {
        return previewInsightCommandHandler.previewInsight(previewInsightQuery);
    }

    @Override
    public DetailInsightResponse detailInsight(DetailInsightQuery detailInsightQuery) {
        return detailInsightCommandHandler.detailInsight(detailInsightQuery);
    }

    @Override
    public ValidateAndEvaluateInsightResponse validateAndEvaluateInsight(ValidateAndEvaluateInsightCommand validateAndEvaluateInsightCommand) {
        // TODO : 캐싱
        return validateAndEvaluateInsightCommandHandler.validateAndEvaluateInsight(validateAndEvaluateInsightCommand);
    }

    @Override
    public CreateInsightResponse createInsight(CreateInsightCommand createInsightCommand) {
        return createInsightCommandHandler.createInsight(createInsightCommand);
    }

    @Override
    public UpdateInsightResponse updateInsight(UpdateInsightCommand updateInsightCommand) {
        return updateInsightCommandHandler.updateInsight(updateInsightCommand);
    }

    @Override
    public DeleteInsightResponse deleteInsight(DeleteInsightCommand deleteInsightCommand) {
        return deleteInsightCommandHandler.deleteInsight(deleteInsightCommand);
    }

    @Override
    public RecommendInsightResponse recommendInsight(RecommendInsightCommand recommendInsightCommand) {
        return recommendInsightCommandHandler.recommendInsight(recommendInsightCommand);
    }

    @Override
    public AccuseInsightResponse accuseInsight(AccuseInsightCommand accuseInsightCommand) {
        return accuseInsightCommandHandler.accuseInsight(accuseInsightCommand);
    }

    @Override
    public RequestInsightResponse requestInsight(RequestInsightCommand requestInsightCommand) {
        return requestInsightCommandHandler.requestInsight(requestInsightCommand);
    }
}
