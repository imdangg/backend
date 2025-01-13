package com.project.imdang.insight.service.application.rest;

import com.project.imdang.insight.service.domain.dto.exchange.request.RequestExchangeInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.accuse.AccuseInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.update.UpdateInsightCommand;
import com.project.imdang.insight.service.domain.valueobject.Access;
import com.project.imdang.insight.service.domain.valueobject.Address;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.domain.valueobject.ComplexEnvironment;
import com.project.imdang.insight.service.domain.valueobject.ComplexFacility;
import com.project.imdang.insight.service.domain.valueobject.FavorableNews;
import com.project.imdang.insight.service.domain.valueobject.Infra;
import com.project.imdang.insight.service.domain.valueobject.ObjectiveItem;
import com.project.imdang.insight.service.domain.valueobject.VisitMethod;
import com.project.imdang.insight.service.domain.valueobject.VisitTime;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public class TestData {

    public TestData() { }
    public TestData(UUID requestedInsightId) {
        this.requestedInsightId = requestedInsightId;
    }

    private UUID requestedInsightId;
//    static UUID requestedInsightId = UUID.fromString("12e15731-8009-4870-a17f-cd7c29faea1b");
//    static Long requestedSnapshotId = 1L;
    public final static UUID requestedMemberId = UUID.fromString("3eff967b-84b8-4ec4-941b-ef3f0a26c0b9");

//    static UUID requestMemberInsightId = UUID.fromString("dac34673-4ef8-4192-ba48-b721ae8bef5b");
//    static Long requestMemberSnapshotId = 2L;
//    public final static Long memberCouponId = 1L;
    public final static UUID requestMemberId = UUID.fromString("d53b9232-b69a-4d46-8ff0-0dabdeb87cab");

    CreateInsightCommand createInsightCommand() {
//        byte[] bytes = "content".getBytes();
//        MockMultipartFile mainImage = new MockMultipartFile("mainImage-1", bytes);
        return CreateInsightCommand.builder()
                .memberId(requestedMemberId)
                .score(80)
//                .mainImage(mainImage)
                .title("title-1")
                .address(TestData.address)
                .apartmentComplex(TestData.apartmentComplex)
                .visitAt(LocalDate.now().minusDays(14))
                .visitTimes(Set.of(VisitTime.아침))
                .visitMethods(Set.of(VisitMethod.대중교통))
                .access(Access.허락_필요)
                .summary("summary-1")
                .infra(TestData.infra)
                .complexEnvironment(TestData.complexEnvironment)
                .complexFacility(TestData.complexFacility)
                .favorableNews(TestData.favorableNews)
                .build();
    }

    UpdateInsightCommand updateInsightCommand() {
        Assert.notNull(requestedInsightId, "InsightId must not be null!");
        return UpdateInsightCommand.builder()
                .insightId(requestedInsightId)
                .score(95)
//                .mainImage(updatedMainImage)
                .title("updated-title-1")
                .address(TestData.address)
                .apartmentComplex(TestData.apartmentComplex)
                .visitAt(LocalDate.now().minusDays(13))
                .visitTimes(Set.of(VisitTime.저녁))
                .visitMethods(Set.of(VisitMethod.자차, VisitMethod.도보))
                .access(Access.허락_필요)
                .summary("updated-summary-1")
                .infra(TestData.updatedInfra)
                .complexEnvironment(TestData.updatedComplexEnvironment)
                .complexFacility(TestData.updatedComplexFacility)
                .favorableNews(TestData.updatedFavorableNews)
                .build();
    }

    DeleteInsightCommand deleteInsightCommand() {
        Assert.notNull(requestedInsightId, "InsightId must not be null!");
        return DeleteInsightCommand.builder()
                .insightId(requestedInsightId)
                .build();
    }

    RecommendInsightCommand recommendInsightCommand() {
        Assert.notNull(requestedInsightId, "InsightId must not be null!");
        return RecommendInsightCommand.builder()
                .insightId(requestedInsightId)
                .build();
    }

    AccuseInsightCommand accuseInsightCommand() {
        Assert.notNull(requestedInsightId, "InsightId must not be null!");
        return AccuseInsightCommand.builder()
                .insightId(requestedInsightId)
                .accuseMemberId(UUID.randomUUID())
                .build();
    }

    RequestExchangeInsightCommand requestExchangeInsightCommand(UUID requestMemberInsightId, Long memberCouponId) {
        Assert.notNull(requestedInsightId, "InsightId must not be null!");
        return RequestExchangeInsightCommand.builder()
                .requestedInsightId(requestedInsightId)
                .requestMemberId(requestMemberId)
                .requestMemberInsightId(requestMemberInsightId)
                .memberCouponId(memberCouponId)
                .build();
    }
/*
    Insight requestedInsight = Insight.builder()
                .id(new InsightId(requestedInsightId))
                .memberId(new MemberId(requestedMemberId))
                .score(80)
                .address(TestData.address)
                .apartmentComplex(TestData.apartmentComplex)
                .title("title-1")
                .contents("contents-1")
                .mainImage("mainImage-1")
                .summary("summary-1")
                .visitAt(ZonedDateTime.now().minusDays(14))
                .visitMethod(VisitMethod.대중교통)
                .access(Access.허락_필요)
                .infra(TestData.infra)
                .complexEnvironment(TestData.complexEnvironment)
                .complexFacility(TestData.complexFacility)
                .favorableNews(TestData.favorableNews)
                .build();

    Snapshot requestedSnapshot = Snapshot.builder()
                .id(new SnapshotId(requestedSnapshotId))
                .insightId(requestedInsight.getId())
                .memberId(requestedInsight.getMemberId())
                .address(requestedInsight.getAddress())
                .apartmentComplex(requestedInsight.getApartmentComplex())
                .title(requestedInsight.getTitle())
                .contents(requestedInsight.getContents())
                .mainImage(requestedInsight.getMainImage())
                .summary(requestedInsight.getSummary())
                .visitAt(requestedInsight.getVisitAt())
                .visitMethod(requestedInsight.getVisitMethod())
                .access(requestedInsight.getAccess())
                .infra(requestedInsight.getInfra())
                .complexEnvironment(requestedInsight.getComplexEnvironment())
                .complexFacility(requestedInsight.getComplexFacility())
                .favorableNews(requestedInsight.getFavorableNews())
                .build();

    Insight requestMemberInsight = Insight.builder()
            .id(new InsightId(requestMemberInsightId))
            .memberId(new MemberId(requestMemberId))
            .score(70)
            .address(TestData.address)
            .apartmentComplex(TestData.apartmentComplex)
            .title("title-2")
            .contents("contents-2")
            .mainImage("mainImage-2")
            .summary("summary-2")
            .visitAt(ZonedDateTime.now().minusDays(10))
            .visitMethod(VisitMethod.도보)
            .access(Access.자유로움)
            .infra(TestData.infra)
            .complexEnvironment(TestData.complexEnvironment)
            .complexFacility(TestData.complexFacility)
            .favorableNews(TestData.favorableNews)
            .build();

    Snapshot requestMemberSnapshot = Snapshot.builder()
            .id(new SnapshotId(requestMemberSnapshotId))
            .insightId(requestMemberInsight.getId())
            .memberId(requestMemberInsight.getMemberId())
            .address(requestMemberInsight.getAddress())
            .apartmentComplex(requestMemberInsight.getApartmentComplex())
            .title(requestMemberInsight.getTitle())
            .contents(requestMemberInsight.getContents())
            .mainImage(requestMemberInsight.getMainImage())
            .summary(requestMemberInsight.getSummary())
            .visitAt(requestMemberInsight.getVisitAt())
            .visitMethod(requestMemberInsight.getVisitMethod())
            .access(requestMemberInsight.getAccess())
            .infra(requestMemberInsight.getInfra())
            .complexEnvironment(requestMemberInsight.getComplexEnvironment())
            .complexFacility(requestMemberInsight.getComplexFacility())
            .favorableNews(requestMemberInsight.getFavorableNews())
            .build();*/

    static Address address = Address.builder()
            .siDo("서울시")
            .siGunGu("강남구")
            .eupMyeonDong("신논현동")
            .buildingNumber("1")
            .build();
    static ApartmentComplex apartmentComplex = ApartmentComplex.builder()
            .name("신논현 더 센트럴 푸르지오")
            .build();

    static Infra infra = Infra.builder()
            .transportations(Set.of(Infra.Transportation.버스_정류장_주변, Infra.Transportation.주차_편리))
            .schoolDistricts(Set.of(Infra.SchoolDistrict.고등학교))
            .amenities(Set.of(Infra.Amenity.병원))
            .facilities(Set.of(Infra.Facility.도서관, Infra.Facility.수영장))
            .surroundings(Set.of(Infra.Surroundings.강, Infra.Surroundings.교회))
            .landmarks(Set.of(Infra.Landmark.고궁, Infra.Landmark.사찰))
            .unpleasantFacilities(Set.of(Infra.UnpleasantFacility.고속도로, Infra.UnpleasantFacility.철도))
            .text("infra_text")
            .build();

    static ComplexEnvironment complexEnvironment = ComplexEnvironment.builder()
            .buildingCondition(ObjectiveItem.좋아요)
            .security(ObjectiveItem.평범해요)
            .childrenFacility(ObjectiveItem.최고예요)
            .seniorFacility(ObjectiveItem.잘_모르겠어요)
            .text("complex_environment_text")
            .build();

    static ComplexFacility complexFacility = ComplexFacility.builder()
            .familyFacilities(Set.of(ComplexFacility.FamilyFacility.경로당))
            .multipurposeFacilities(Set.of(ComplexFacility.MultipurposeFacility.다목적실))
            .leisureFacilities(Set.of(ComplexFacility.LeisureFacility.독서실, ComplexFacility.LeisureFacility.조식))
            .surroundings(Set.of(ComplexFacility.Surroundings.분수, ComplexFacility.Surroundings.벤치))
            .text("complex_facility_text")
            .build();

    static FavorableNews favorableNews = FavorableNews.builder()
            .transportations(Set.of(FavorableNews.Transportation.고속철도역_신설, FavorableNews.Transportation.지하철_개통))
            .developments(Set.of(FavorableNews.Development.재개발, FavorableNews.Development.재건축))
            .educations(Set.of(FavorableNews.Education.초등학교_신설_예정, FavorableNews.Education.고등학교_신설_예정))
            .environments(Set.of(FavorableNews.Environment.하천_복원, FavorableNews.Environment.대형공원))
            .cultures(Set.of(FavorableNews.Culture.대형병원, FavorableNews.Culture.문화센터))
            .industries(Set.of(FavorableNews.Industry.산업단지, FavorableNews.Industry.기업_이전))
            .policies(Set.of(FavorableNews.Policy.잘_모르겠어요))
            .text("favorable_news_text")
            .build();

    static Infra updatedInfra = Infra.builder()
            .transportations(Set.of(Infra.Transportation.해당_없음))
            .schoolDistricts(Set.of(Infra.SchoolDistrict.어린이집, Infra.SchoolDistrict.초품아))
            .amenities(Set.of(Infra.Amenity.해당_없음))
            .facilities(Set.of(Infra.Facility.해당_없음))
            .surroundings(Set.of(Infra.Surroundings.해당_없음))
            .landmarks(Set.of(Infra.Landmark.해당_없음))
            .unpleasantFacilities(Set.of(Infra.UnpleasantFacility.고속도로, Infra.UnpleasantFacility.철도))
            .text("updated_infra_text")
            .build();

    static ComplexEnvironment updatedComplexEnvironment = ComplexEnvironment.builder()
            .buildingCondition(ObjectiveItem.별로에요)
            .security(ObjectiveItem.별로에요)
            .childrenFacility(ObjectiveItem.별로에요)
            .seniorFacility(ObjectiveItem.별로에요)
            .text("updated_complex_environment_text")
            .build();

    static ComplexFacility updatedComplexFacility = ComplexFacility.builder()
            .familyFacilities(Set.of(ComplexFacility.FamilyFacility.해당_없음))
            .multipurposeFacilities(Set.of(ComplexFacility.MultipurposeFacility.해당_없음))
            .leisureFacilities(Set.of(ComplexFacility.LeisureFacility.해당_없음))
            .surroundings(Set.of(ComplexFacility.Surroundings.해당_없음))
            .text("updated_complex_facility_text")
            .build();

    static FavorableNews updatedFavorableNews = FavorableNews.builder()
            .transportations(Set.of(FavorableNews.Transportation.잘_모르겠어요))
            .developments(Set.of(FavorableNews.Development.잘_모르겠어요))
            .educations(Set.of(FavorableNews.Education.잘_모르겠어요))
            .environments(Set.of(FavorableNews.Environment.잘_모르겠어요))
            .cultures(Set.of(FavorableNews.Culture.잘_모르겠어요))
            .industries(Set.of(FavorableNews.Industry.잘_모르겠어요))
            .policies(Set.of(FavorableNews.Policy.잘_모르겠어요))
            .text("updated_favorable_news_text")
            .build();
}
