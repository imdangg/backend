package com.project.imdang.insight.domain.ports.input.service;

import com.project.imdang.common.domain.valueobject.ApartmentComplex;
import com.project.imdang.common.domain.valueobject.District;
import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.dto.insight.accuse.AccuseInsightCommand;
import com.project.imdang.insight.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.domain.dto.insight.delete.DeleteInsightCommand;
import com.project.imdang.insight.domain.dto.insight.detail.DetailInsightQuery;
import com.project.imdang.insight.domain.dto.insight.detail.InsightDetailResult;
import com.project.imdang.insight.domain.dto.insight.list.*;
import com.project.imdang.insight.domain.dto.insight.recommend.RecommendInsightCommand;
import com.project.imdang.insight.domain.dto.insight.update.UpdateInsightCommand;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InsightApplicationService {
    Page<InsightResult> listInsight(ListInsightQuery listInsightQuery);
    Page<InsightResult> listWithImages(ListInsightQuery listInsightQuery);
    Page<InsightResult> listInsightByDate(ListInsightByDateQuery listInsightByDateQuery);
    Page<InsightResult> listInsightByDistrict(ListInsightByDistrictQuery listInsightByDistrictQuery);
    Page<InsightResult> listInsightByApartmentComplex(ListInsightByApartmentComplexQuery listInsightByApartmentComplexQuery);
    Page<InsightResult> listInsightWithImagesByApartmentComplex(ListInsightByApartmentComplexQuery listInsightByApartmentComplexQuery);
    List<ApartmentComplex> listMyVisitedApartmentComplex(MemberId memberId);
    Page<InsightResult> listInsightWithImagesByAddress(ListInsightByAddressQuery listInsightByAddressQuery);
    Page<District> listDistrict(String siDo, String siGunGu, Integer pageNumber, Integer pageSize);

    List<MyDistrictResult> listMyInsightDistrict(MemberId memberId);
    List<MyApartmentComplexResult> listMyInsightApartmentComplexByDistrict(MemberId memberId, String siDo, String siGunGu, String eupMyeonDong);
    Page<InsightResult> listMyInsight(ListMyInsightQuery listMyInsightQuery);
    Page<InsightSimpleResult> listMyInsightCreatedByMe(ListMyInsightCreatedByMeQuery listMyInsightCreatedByMeQuery);

    InsightDetailResult detailInsight(DetailInsightQuery detailInsightQuery);
    // = uploadInsight
    InsightId createInsight(CreateInsightCommand createInsightCommand);
    InsightId updateInsight(UpdateInsightCommand updateInsightCommand);
    InsightId deleteInsight(DeleteInsightCommand deleteInsightCommand);

    InsightId recommendInsight(RecommendInsightCommand recommendInsightCommand);
    InsightId accuseInsight(AccuseInsightCommand accuseInsightCommand);
}
