package com.project.imdang.insight.service.domain;

import com.project.imdang.insight.service.domain.dto.insight.accuse.AccuseInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.accuse.AccuseInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.detail.DetailInsightQuery;
import com.project.imdang.insight.service.domain.dto.insight.detail.DetailInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.InsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.list.ListInsightByApartmentComplexQuery;
import com.project.imdang.insight.service.domain.dto.insight.list.ListInsightQuery;
import com.project.imdang.insight.service.domain.dto.insight.list.ListMyInsightQuery;
import com.project.imdang.insight.service.domain.dto.insight.list.MyInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightResponse;
import com.project.imdang.insight.service.domain.dto.insight.update.UpdateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.update.UpdateInsightResponse;
import com.project.imdang.insight.service.domain.handler.insight.AccuseInsightCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.CountMyInsightByAddressCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.CreateInsightCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.DeleteInsightCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.DetailInsightCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.ListInsightByApartmentComplexCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.ListInsightCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.ListMyInsightAddressCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.ListMyInsightCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.ListMyVisitedApartmentComplexCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.RecommendInsightCommandHandler;
import com.project.imdang.insight.service.domain.handler.insight.UpdateInsightCommandHandler;
import com.project.imdang.insight.service.domain.ports.input.service.InsightApplicationService;
import com.project.imdang.insight.service.domain.valueobject.Address;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
@RequiredArgsConstructor
@Service
public class InsightApplicationServiceImpl implements InsightApplicationService {

    private final ListInsightCommandHandler listInsightCommandHandler;
    private final ListInsightByApartmentComplexCommandHandler listInsightByApartmentComplexCommandHandler;
    private final ListMyVisitedApartmentComplexCommandHandler listMyVisitedApartmentComplexCommandHandler;


    private final ListMyInsightAddressCommandHandler listMyInsightAddressCommandHandler;
    private final CountMyInsightByAddressCommandHandler countMyInsightByAddressCommandHandler;
    private final ListMyInsightCommandHandler listMyInsightCommandHandler;

    private final DetailInsightCommandHandler detailInsightCommandHandler;
    private final CreateInsightCommandHandler createInsightCommandHandler;
    private final UpdateInsightCommandHandler updateInsightCommandHandler;
    private final DeleteInsightCommandHandler deleteInsightCommandHandler;

    private final RecommendInsightCommandHandler recommendInsightCommandHandler;
    private final AccuseInsightCommandHandler accuseInsightCommandHandler;

    @Override
    public Page<InsightResponse> listInsight(ListInsightQuery listInsightQuery) {
        return listInsightCommandHandler.listInsight(listInsightQuery);
    }

    @Override
    public Page<InsightResponse> listInsightByApartmentComplex(ListInsightByApartmentComplexQuery listInsightByApartmentComplexQuery) {
        return listInsightByApartmentComplexCommandHandler.listInsightByApartmentComplex(listInsightByApartmentComplexQuery);
    }

    @Override
    public List<ApartmentComplex> listMyVisitedApartmentComplex(UUID memberId) {
        return listMyVisitedApartmentComplexCommandHandler.listMyVisitedApartmentComplex(memberId);
    }

    @Override
    public List<Address> listMyInsightAddress(UUID memberId) {
        return listMyInsightAddressCommandHandler.listMyInsightAddress(memberId);
    }

    @Override
    public MyInsightResponse countMyInsightByAddress(UUID memberId, Address address) {
        return countMyInsightByAddressCommandHandler.countMyInsightByAddress(memberId, address);
    }

    @Override
    public Page<InsightResponse> listMyInsight(ListMyInsightQuery listMyInsightQuery) {
        return listMyInsightCommandHandler.listMyInsight(listMyInsightQuery);
    }

    @Override
    public DetailInsightResponse detailInsight(DetailInsightQuery detailInsightQuery) {
        return detailInsightCommandHandler.detailInsight(detailInsightQuery);
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
}
