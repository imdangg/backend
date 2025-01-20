package com.project.imdang.insight.service.domain.ports.input.service;

import com.project.imdang.insight.service.domain.dto.insight.accuse.AccuseInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.accuse.AccuseInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.detail.DetailInsightQuery;
import com.project.imdang.insight.service.domain.dto.insight.detail.DetailInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.ApartmentComplexResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.DistrictResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightSimpleResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.ListInsightByApartmentComplexQuery;
import com.project.imdang.insight.service.domain.dto.insight.list.ListInsightQuery;
import com.project.imdang.insight.service.domain.dto.insight.list.ListMyInsightCreatedByMeQuery;
import com.project.imdang.insight.service.domain.dto.insight.list.ListMyInsightQuery;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.update.UpdateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.update.UpdateInsightResponse;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.domain.valueobject.District;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface InsightApplicationService {
    Page<InsightResponse> listInsight(ListInsightQuery listInsightQuery);
    Page<InsightResponse> listInsightByApartmentComplex(ListInsightByApartmentComplexQuery listInsightByApartmentComplexQuery);
    List<ApartmentComplex> listMyVisitedApartmentComplex(UUID memberId);

    List<DistrictResponse> listMyInsightDistrict(UUID memberId);
    List<ApartmentComplexResponse> listMyInsightApartmentComplexByDistrict(UUID memberId, District district);
    Page<InsightResponse> listMyInsight(ListMyInsightQuery listMyInsightQuery);
    Page<InsightSimpleResponse> listMyInsightCreatedByMe(ListMyInsightCreatedByMeQuery listMyInsightCreatedByMeQuery);

    DetailInsightResponse detailInsight(DetailInsightQuery detailInsightQuery);
    // = uploadInsight
    CreateInsightResponse createInsight(CreateInsightCommand createInsightCommand);
    UpdateInsightResponse updateInsight(UpdateInsightCommand updateInsightCommand);
    DeleteInsightResponse deleteInsight(DeleteInsightCommand deleteInsightCommand);

    RecommendInsightResponse recommendInsight(RecommendInsightCommand recommendInsightCommand);
    AccuseInsightResponse accuseInsight(AccuseInsightCommand accuseInsightCommand);
}
