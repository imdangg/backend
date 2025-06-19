package com.project.imdang.insight.application.mapper;

import com.project.imdang.common.domain.valueobject.Address;
import com.project.imdang.common.domain.valueobject.ApartmentComplex;
import com.project.imdang.common.domain.valueobject.ComplexEnvironment;
import com.project.imdang.common.domain.valueobject.File;
import com.project.imdang.common.domain.valueobject.Infra;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.application.dto.insight.AddressDTO;
import com.project.imdang.insight.application.dto.insight.ApartmentComplexDTO;
import com.project.imdang.insight.application.dto.insight.ComplexEnvironmentDTO;
import com.project.imdang.insight.application.dto.insight.CreateInsightRequest;
import com.project.imdang.insight.application.dto.insight.InfraDTO;
import com.project.imdang.insight.application.dto.insight.UpdateInsightRequest;
import com.project.imdang.insight.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.domain.dto.insight.update.UpdateInsightCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class InsightRequestResolver {

    public CreateInsightCommand toCreateInsightCommand(UUID memberId,
                                                       List<File> mainImages,
                                                       CreateInsightRequest createInsightRequest) {
        return CreateInsightCommand.builder()
                .memberId(new MemberId(memberId))
                .images(mainImages)
                .title(createInsightRequest.getTitle())
                .address(toAddress(createInsightRequest.getAddress()))
                .apartmentComplex(toApartmentComplex(createInsightRequest.getApartmentComplex()))
                .visitAt(createInsightRequest.getVisitAt())
                .visitTimes(createInsightRequest.getVisitTimes())
                .visitMethods(createInsightRequest.getVisitMethods())
                .summary(createInsightRequest.getSummary())
                .access(createInsightRequest.getAccess())
                .infra(toInfra(createInsightRequest.getInfra()))
                .complexEnvironment(toComplexEnvironment(createInsightRequest.getComplexEnvironment()))
                .build();
    }

    public UpdateInsightCommand toUpdateInsightCommand(UUID memberId,
                                                       List<File> mainImage,
                                                       UpdateInsightRequest updateInsightRequest) {
        return UpdateInsightCommand.builder()
                .memberId(new MemberId(memberId))
                .score(updateInsightRequest.getScore())
                .mainImage(mainImage)
                .title(updateInsightRequest.getTitle())
                .address(toAddress(updateInsightRequest.getAddress()))
                .apartmentComplex(toApartmentComplex(updateInsightRequest.getApartmentComplex()))
                .visitAt(updateInsightRequest.getVisitAt())
                .visitTimes(updateInsightRequest.getVisitTimes())
                .visitMethods(updateInsightRequest.getVisitMethods())
                .summary(updateInsightRequest.getSummary())
                .access(updateInsightRequest.getAccess())
                .infra(toInfra(updateInsightRequest.getInfra()))
                .complexEnvironment(toComplexEnvironment(updateInsightRequest.getComplexEnvironment()))
                .build();
    }

    private static Address toAddress(AddressDTO address) {
        return Address.builder()
                .siDo(address.siDo())
                .siGunGu(address.siGunGu())
                .eupMyeonDong(address.eupMyeonDong())
                .roadName(address.roadName())
                .buildingNumber(address.buildingNumber())
                .detail(address.detail())
                .latitude(address.latitude())
                .longitude(address.longitude())
                .build();
    }

    private static ApartmentComplex toApartmentComplex(ApartmentComplexDTO apartmentComplex) {
        return ApartmentComplex.builder()
                .name(apartmentComplex.name())
                .build();
    }

    private static Infra toInfra(InfraDTO infra) {
        return Infra.builder()
                .transportations(infra.transportations())
                .schoolDistricts(infra.schoolDistricts())
                .amenities(infra.amenities())
                .facilities(infra.facilities())
                .surroundings(infra.surroundings())
                .text(infra.text())
                .build();
    }

    private static ComplexEnvironment toComplexEnvironment(ComplexEnvironmentDTO complexEnvironment) {
        return ComplexEnvironment.builder()
                .buildingCondition(complexEnvironment.buildingCondition())
                .security(complexEnvironment.security())
                .childrenFacility(complexEnvironment.childrenFacility())
                .text(complexEnvironment.text())
                .build();
    }
}
