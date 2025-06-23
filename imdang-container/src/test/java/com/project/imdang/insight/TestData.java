package com.project.imdang.insight;

import com.project.imdang.common.domain.valueobject.Access;
import com.project.imdang.common.domain.valueobject.Address;
import com.project.imdang.common.domain.valueobject.ApartmentComplex;
import com.project.imdang.common.domain.valueobject.ComplexEnvironment;
import com.project.imdang.common.domain.valueobject.Infra;
import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.common.domain.valueobject.ObjectiveItem;
import com.project.imdang.common.domain.valueobject.VisitMethod;
import com.project.imdang.common.domain.valueobject.VisitTime;
import com.project.imdang.insight.application.dto.insight.*;
import com.project.imdang.insight.domain.dto.insight.accuse.AccuseInsightCommand;
import com.project.imdang.insight.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.domain.dto.insight.delete.DeleteInsightCommand;
import com.project.imdang.insight.domain.dto.insight.recommend.RecommendInsightCommand;
import com.project.imdang.insight.domain.dto.insight.update.UpdateInsightCommand;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public class TestData {

//    public final static UUID memberId = UUID.fromString("3eff967b-84b8-4ec4-941b-ef3f0a26c0b9");
    public final static UUID memberId = UUID.fromString("fb2b3270-65e3-4006-a994-71e74a676a8a");
    private InsightId insightId;

    public TestData() {}

    public TestData(UUID insightId) {
        this.insightId = new InsightId(insightId);
    }

    CreateInsightCommand createInsightCommand() {
//        byte[] bytes = "content".getBytes();
//        MockMultipartFile mainImage = new MockMultipartFile("mainImage-1", bytes);
        return CreateInsightCommand.builder()
                .memberId(new MemberId(memberId))
//                .mainImage(mainImage)
                .title("title-1")
                .address(TestData.address)
                .apartmentComplex(TestData.apartmentComplex)
                .visitAt(LocalDate.now().minusDays(14))
                .visitTimes(Set.of(VisitTime.아침))
                .visitMethods(Set.of(VisitMethod.대중교통))
                .access(Access.허락시_가능)
                .summary("지하철역과 도보 10분 거리로 접근성이 좋지만, 근처 공사로 소음 문제가 있을 수 있을 것 같아요. 지하철역과 도보 10분 거리로 접근성이 좋지만, 근처 공사로 소음 문제가 있을 수 있을 것 같아요. 하지만 단지 내 공원이 잘 조성되어 있어 가족 단위 거주자에게 적합할 것 같아요.")
                .infra(TestData.infra)
                .complexEnvironment(TestData.complexEnvironment)
                .build();
    }

    CreateInsightRequest createInsightRequest() {
//        byte[] bytes = "content".getBytes();
//        MockMultipartFile mainImage = new MockMultipartFile("mainImage-1", bytes);
        return CreateInsightRequest.builder()
                .score(80)
//                .mainImage(mainImage)
                .title("title-1")
                .address(TestData.addressDTO)
                .apartmentComplex(TestData.apartmentComplexDTO)
                .visitAt(LocalDate.now().minusDays(14))
                .visitTimes(Set.of(VisitTime.아침))
                .visitMethods(Set.of(VisitMethod.대중교통))
                .summary("지하철역과 도보 10분 거리로 접근성이 좋지만, 근처 공사로 소음 문제가 있을 수 있을 것 같아요. 지하철역과 도보 10분 거리로 접근성이 좋지만, 근처 공사로 소음 문제가 있을 수 있을 것 같아요. 하지만 단지 내 공원이 잘 조성되어 있어 가족 단위 거주자에게 적합할 것 같아요.")
                .access(Access.허락시_가능)
                .infra(TestData.infraDTO)
                .complexEnvironment(TestData.complexEnvironmentDTO)
                .build();
    }

    UpdateInsightCommand updateInsightCommand() {
        Assert.notNull(insightId, "InsightId must not be null!");
        return UpdateInsightCommand.builder()
                .insightId(insightId)
//                .mainImage(updatedMainImage)
                .title("updated-title-1")
                .address(TestData.address)
                .apartmentComplex(TestData.apartmentComplex)
                .visitAt(LocalDate.now().minusDays(13))
                .visitTimes(Set.of(VisitTime.저녁))
                .visitMethods(Set.of(VisitMethod.자차, VisitMethod.도보))
                .access(Access.허락시_가능)
                .summary("updated-summary-1")
                .infra(TestData.updatedInfra)
                .complexEnvironment(TestData.updatedComplexEnvironment)
                .build();
    }

    UpdateInsightRequest updateInsightRequest() {
        Assert.notNull(insightId, "InsightId must not be null!");
        return UpdateInsightRequest.builder()
                .insightId(insightId.getValue())
                .title("updated-title-1")
                .address(TestData.addressDTO)
                .apartmentComplex(TestData.apartmentComplexDTO)
                .visitAt(LocalDate.now().minusDays(13))
                .visitTimes(Set.of(VisitTime.저녁))
                .visitMethods(Set.of(VisitMethod.자차, VisitMethod.도보))
                .access(Access.허락시_가능)
                .summary("수정 - 지하철역과 도보 10분 거리로 접근성이 좋지만, 근처 공사로 소음 문제가 있을 수 있을 것 같아요. 지하철역과 도보 10분 거리로 접근성이 좋지만, 근처 공사로 소음 문제가 있을 수 있을 것 같아요. 하지만 단지 내 공원이 잘 조성되어 있어 가족 단위 거주자에게 적합할 것 같아요.")
                .infra(TestData.infraDTO)
                .complexEnvironment(TestData.complexEnvironmentDTO)
                .build();
    }

    DeleteInsightCommand deleteInsightCommand() {
        Assert.notNull(insightId, "InsightId must not be null!");
        return DeleteInsightCommand.builder()
                .insightId(insightId)
                .build();
    }

    DeleteInsightRequest deleteInsightRequest() {
        Assert.notNull(insightId, "InsightId must not be null!");
        return DeleteInsightRequest.builder()
                .insightId(insightId.getValue())
                .build();
    }

    RecommendInsightCommand recommendInsightCommand() {
        Assert.notNull(insightId, "InsightId must not be null!");
        return RecommendInsightCommand.builder()
                .insightId(insightId)
                .build();
    }

    AccuseInsightCommand accuseInsightCommand() {
        Assert.notNull(insightId, "InsightId must not be null!");
        return AccuseInsightCommand.builder()
                .insightId(insightId)
                .accuseMemberId(new MemberId(UUID.randomUUID()))
                .build();
    }

    static Address address = Address.builder()
            .siDo("서울특별시")
            .siGunGu("종로구")
            .eupMyeonDong("효제동")
            .buildingNumber("1")
            .build();

    static AddressDTO addressDTO = new AddressDTO(
            "서울특별시",
            "종로구",
            "효제동",
            "율곡로",
            "191",
            "101동 202호",
            12.345678,
            123.45678
    );

    static ApartmentComplex apartmentComplex = ApartmentComplex.builder()
            .name("신논현 더 센트럴 푸르지오")
            .build();

    static ApartmentComplexDTO apartmentComplexDTO = new ApartmentComplexDTO(
            "신논현 더 센트럴 푸르지오"
    );

    static Infra infra = Infra.builder()
            .transportations(Set.of(Infra.Transportation.버스_정류장_주변, Infra.Transportation.주차_편리))
            .schoolDistricts(Set.of(Infra.SchoolDistrict.고등학교))
            .amenities(Set.of(Infra.Amenity.병원))
            .facilities(Set.of(Infra.Facility.도서관, Infra.Facility.수영장))
            .surroundings(Set.of(Infra.Surroundings.강, Infra.Surroundings.교회))
            .text("infra_text")
            .build();

    static InfraDTO infraDTO = new InfraDTO(
            Set.of(Infra.Transportation.버스_정류장_주변, Infra.Transportation.주차_편리),
            Set.of(Infra.SchoolDistrict.고등학교),
            Set.of(Infra.Amenity.병원),
            Set.of(Infra.Facility.도서관,Infra.Facility.수영장),
            Set.of(Infra.Surroundings.강,Infra.Surroundings.교회),
            "infra_text"
    );

    static ComplexEnvironment complexEnvironment = ComplexEnvironment.builder()
            .buildingCondition(ObjectiveItem.좋아요)
            .security(ObjectiveItem.평범해요)
            .childrenFacility(ObjectiveItem.최고예요)
            .text("complex_environment_text")
            .build();

    public static final ComplexEnvironmentDTO complexEnvironmentDTO = new ComplexEnvironmentDTO(
            ObjectiveItem.좋아요,
            ObjectiveItem.평범해요,
            ObjectiveItem.최고예요,
            "complex_environment_text"
    );

    static Infra updatedInfra = Infra.builder()
            .transportations(Set.of(Infra.Transportation.해당_없음))
            .schoolDistricts(Set.of(Infra.SchoolDistrict.어린이집, Infra.SchoolDistrict.초품아))
            .amenities(Set.of(Infra.Amenity.해당_없음))
            .facilities(Set.of(Infra.Facility.해당_없음))
            .surroundings(Set.of(Infra.Surroundings.해당_없음))
            .text("updated_infra_text")
            .build();

    static ComplexEnvironment updatedComplexEnvironment = ComplexEnvironment.builder()
            .buildingCondition(ObjectiveItem.별로에요)
            .security(ObjectiveItem.별로에요)
            .childrenFacility(ObjectiveItem.별로에요)
            .text("updated_complex_environment_text")
            .build();
}
