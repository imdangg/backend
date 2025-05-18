package com.project.imdang.insight.domain;

import com.project.imdang.common.domain.valueobject.ApartmentComplex;
import com.project.imdang.common.domain.valueobject.District;
import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.dto.insight.accuse.AccuseInsightCommand;
import com.project.imdang.insight.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.domain.dto.insight.delete.DeleteInsightCommand;
import com.project.imdang.insight.domain.dto.insight.detail.DetailInsightQuery;
import com.project.imdang.insight.domain.dto.insight.detail.InsightDetailResult;
import com.project.imdang.insight.domain.dto.insight.list.MyApartmentComplexResult;
import com.project.imdang.insight.domain.dto.insight.list.MyDistrictResult;
import com.project.imdang.insight.domain.dto.insight.list.InsightResult;
import com.project.imdang.insight.domain.dto.insight.list.InsightSimpleResult;
import com.project.imdang.insight.domain.dto.insight.list.ListInsightByApartmentComplexQuery;
import com.project.imdang.insight.domain.dto.insight.list.ListInsightByDateQuery;
import com.project.imdang.insight.domain.dto.insight.list.ListInsightByDistrictQuery;
import com.project.imdang.insight.domain.dto.insight.list.ListInsightQuery;
import com.project.imdang.insight.domain.dto.insight.list.ListMyInsightCreatedByMeQuery;
import com.project.imdang.insight.domain.dto.insight.list.ListMyInsightQuery;
import com.project.imdang.insight.domain.dto.insight.recommend.RecommendInsightCommand;
import com.project.imdang.insight.domain.dto.insight.update.UpdateInsightCommand;
import com.project.imdang.insight.domain.handler.insight.AccuseInsightCommandHandler;
import com.project.imdang.insight.domain.handler.insight.CreateInsightCommandHandler;
import com.project.imdang.insight.domain.handler.insight.DeleteInsightCommandHandler;
import com.project.imdang.insight.domain.handler.insight.DetailInsightQueryHandler;
import com.project.imdang.insight.domain.handler.insight.ListDistrictQueryHandler;
import com.project.imdang.insight.domain.handler.insight.ListInsightQueryHandler;
import com.project.imdang.insight.domain.handler.insight.ListMyInsightApartmentComplexByDistrictQueryHandler;
import com.project.imdang.insight.domain.handler.insight.ListMyInsightCreatedByMeQueryHandler;
import com.project.imdang.insight.domain.handler.insight.ListMyInsightDistrictQueryHandler;
import com.project.imdang.insight.domain.handler.insight.ListMyInsightQueryHandler;
import com.project.imdang.insight.domain.handler.insight.ListMyVisitedApartmentComplexQueryHandler;
import com.project.imdang.insight.domain.handler.insight.RecommendInsightCommandHandler;
import com.project.imdang.insight.domain.handler.insight.UpdateInsightCommandHandler;
import com.project.imdang.insight.domain.ports.input.service.InsightApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@RequiredArgsConstructor
@Service
public class InsightApplicationServiceImpl implements InsightApplicationService {

    private final ListInsightQueryHandler listInsightQueryHandler;
    private final ListMyVisitedApartmentComplexQueryHandler listMyVisitedApartmentComplexQueryHandler;
    private final ListDistrictQueryHandler listDistrictQueryHandler;

    private final ListMyInsightDistrictQueryHandler listMyInsightDistrictQueryHandler;
    private final ListMyInsightApartmentComplexByDistrictQueryHandler listMyInsightApartmentComplexByDistrictQueryHandler;
    private final ListMyInsightQueryHandler listMyInsightQueryHandler;
    private final ListMyInsightCreatedByMeQueryHandler listMyInsightCreatedByMeQueryHandler;

    private final DetailInsightQueryHandler detailInsightQueryHandler;
    private final CreateInsightCommandHandler createInsightCommandHandler;
    private final UpdateInsightCommandHandler updateInsightCommandHandler;
    private final DeleteInsightCommandHandler deleteInsightCommandHandler;

    private final RecommendInsightCommandHandler recommendInsightCommandHandler;
    private final AccuseInsightCommandHandler accuseInsightCommandHandler;

    @Override
    public Page<InsightResult> listInsight(ListInsightQuery listInsightQuery) {
        return listInsightQueryHandler.list(listInsightQuery);
    }

    @Override
    public Page<InsightResult> listInsightByDate(ListInsightByDateQuery listInsightByDateQuery) {
        return listInsightQueryHandler.listByDate(listInsightByDateQuery);
    }

    @Override
    public Page<InsightResult> listInsightByDistrict(ListInsightByDistrictQuery listInsightByDistrictQuery) {
        return listInsightQueryHandler.listByDistrict(listInsightByDistrictQuery);
    }

    @Override
    public Page<InsightResult> listInsightByApartmentComplex(ListInsightByApartmentComplexQuery listInsightByApartmentComplexQuery) {
        return listInsightQueryHandler.listByApartmentComplex(listInsightByApartmentComplexQuery);
    }

    @Override
    public List<ApartmentComplex> listMyVisitedApartmentComplex(MemberId memberId) {
        return listMyVisitedApartmentComplexQueryHandler.listMyVisitedApartmentComplex(memberId);
    }

    @Override
    public Page<District> listDistrict(String siDo, String siGunGu, Integer pageNumber, Integer pageSize) {
        return listDistrictQueryHandler.listDistrict(siDo, siGunGu, pageNumber, pageSize);
    }

    @Override
    public List<MyDistrictResult> listMyInsightDistrict(MemberId memberId) {
        return listMyInsightDistrictQueryHandler.listMyInsightDistrict(memberId);
    }

    @Override
    public List<MyApartmentComplexResult> listMyInsightApartmentComplexByDistrict(MemberId memberId, String siDo, String siGunGu, String eupMyeonDong) {
        final District district = District.builder()
                .siDo(siDo)
                .siGunGu(siGunGu)
                .eupMyeonDong(eupMyeonDong)
                .build();
        return listMyInsightApartmentComplexByDistrictQueryHandler.listMyInsightApartmentComplexByDistrict(memberId, district);
    }

    @Override
    public Page<InsightResult> listMyInsight(ListMyInsightQuery listMyInsightQuery) {
        return listMyInsightQueryHandler.listMyInsight(listMyInsightQuery);
    }

    @Override
    public Page<InsightSimpleResult> listMyInsightCreatedByMe(ListMyInsightCreatedByMeQuery listMyInsightCreatedByMeQuery) {
        return listMyInsightCreatedByMeQueryHandler.listMyInsightCreatedByMe(listMyInsightCreatedByMeQuery);
    }

    @Override
    public InsightDetailResult detailInsight(DetailInsightQuery detailInsightQuery) {
        return detailInsightQueryHandler.detailInsight(detailInsightQuery);
    }

    @Override
    public InsightId createInsight(CreateInsightCommand createInsightCommand) {
        return createInsightCommandHandler.createInsight(createInsightCommand);
    }

    @Override
    public InsightId updateInsight(UpdateInsightCommand updateInsightCommand) {
        return updateInsightCommandHandler.updateInsight(updateInsightCommand);
    }

    @Override
    public InsightId deleteInsight(DeleteInsightCommand deleteInsightCommand) {
        return deleteInsightCommandHandler.deleteInsight(deleteInsightCommand);
    }

    @Override
    public InsightId recommendInsight(RecommendInsightCommand recommendInsightCommand) {
        return recommendInsightCommandHandler.recommendInsight(recommendInsightCommand);
    }

    @Override
    public InsightId accuseInsight(AccuseInsightCommand accuseInsightCommand) {
        return accuseInsightCommandHandler.accuseInsight(accuseInsightCommand);
    }
}
