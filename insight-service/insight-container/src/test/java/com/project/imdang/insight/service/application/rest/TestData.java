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
import com.project.imdang.insight.service.domain.valueobject.Opinion;
import com.project.imdang.insight.service.domain.valueobject.VisitMethod;
import org.springframework.util.Assert;

import java.time.ZonedDateTime;
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
        return CreateInsightCommand.builder()
                .memberId(requestedMemberId)
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
    }

    UpdateInsightCommand updateInsightCommand() {
        Assert.notNull(requestedInsightId, "InsightId must not be null!");
        return UpdateInsightCommand.builder()
                .insightId(requestedInsightId)
                .score(95)
                .title("updated-title-1")
                .contents("updated-contents-1")
                .mainImage("updated-mainImage-1")
                .summary("updated-summary-1")
                .visitAt(ZonedDateTime.now().minusDays(3))
                .visitMethod(VisitMethod.자차)
                .access(Access.자유로움)
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
            .siGunGu("강남구")
            .dong("신논현동")
            .build();
    static ApartmentComplex apartmentComplex = ApartmentComplex.builder()
            .name("신논현 더 센트럴 푸르지오")
            .build();

    static Infra infra = Infra.builder()
            .transportation(Opinion.<Set<Infra.Transportation>>builder()
                    .choice(Set.of(Infra.Transportation.버스정류장_가까움, Infra.Transportation.주차_편리))
                    .text("infra_transportation_text")
                    .build())
            .schoolDistrict(Opinion.<Set<Infra.SchoolDistrict>>builder()
                    .choice(Set.of(Infra.SchoolDistrict.고등학교))
                    .text("infra_schoolDistrict_text")
                    .build())
            .amenity(Opinion.<Set<Infra.Amenity>>builder()
                    .choice(Set.of(Infra.Amenity.병원))
                    .text("infra_amenity_text")
                    .build())
            .facility(Opinion.<Set<Infra.Facility>>builder()
                    .choice(Set.of(Infra.Facility.도서관, Infra.Facility.수영장))
                    .text("infra_facility_text")
                    .build())
            .surroundings(Opinion.<Set<Infra.Surroundings>>builder()
                    .choice(Set.of(Infra.Surroundings.강, Infra.Surroundings.교회))
                    .text("infra_surroundings_text")
                    .build())
            .landmark(Opinion.<Set<Infra.Landmark>>builder()
                    .choice(Set.of(Infra.Landmark.고궁, Infra.Landmark.사찰))
                    .text("infra_landmark_text")
                    .build())
            .unpleasantFacility(Opinion.<Set<Infra.UnpleasantFacility>>builder()
                    .choice(Set.of(Infra.UnpleasantFacility.고속도로, Infra.UnpleasantFacility.철도))
                    .text("infra_unpleasantFacility_text")
                    .build())
            .build();

    static ComplexEnvironment complexEnvironment = ComplexEnvironment.builder()
            .buildingCondition(Opinion.<ObjectiveItem>builder()
                    .choice(ObjectiveItem.좋아요)
                    .text("complexEnvironment_buildingCondition_text")
                    .build())
            .security(Opinion.<ObjectiveItem>builder()
                    .choice(ObjectiveItem.별로예요)
                    .text("complexEnvironment_security_text")
                    .build())
            .childrenFacility(Opinion.<ObjectiveItem>builder()
                    .choice(ObjectiveItem.최고예요)
                    .text("complexEnvironment_childrenFacility_text")
                    .build())
            .seniorFacility(Opinion.<ObjectiveItem>builder()
                    .choice(ObjectiveItem.확인이_어려워요)
                    .text("complexEnvironment_seniorFacility_text")
                    .build())
            .build();

    static ComplexFacility complexFacility = ComplexFacility.builder()
            .family(Opinion.<Set<ComplexFacility.Family>>builder()
                    .choice(Set.of(ComplexFacility.Family.경로당))
                    .text("complexFacility_family_text")
                    .build())
            .multipurpose(Opinion.<Set<ComplexFacility.Multipurpose>>builder()
                    .choice(Set.of(ComplexFacility.Multipurpose.다목적실))
                    .text("complexFacility_multipurpose_text")
                    .build())
            .leisure(Opinion.<Set<ComplexFacility.Leisure>>builder()
                    .choice(Set.of(ComplexFacility.Leisure.독서실, ComplexFacility.Leisure.조식))
                    .text("complexFacility_leisure_text")
                    .build())
            .surroundings(Opinion.<Set<ComplexFacility.Surroundings>>builder()
                    .choice(Set.of(ComplexFacility.Surroundings.분수, ComplexFacility.Surroundings.벤치))
                    .text("complexFacility_surroundings_text")
                    .build())
            .build();

    static FavorableNews favorableNews = FavorableNews.builder()
            .transportation(Opinion.<Set<FavorableNews.Transportation>>builder()
                    .choice(Set.of(FavorableNews.Transportation.고속철도역_신설, FavorableNews.Transportation.지하철_개통))
                    .text("favorableNews_transportation_text")
                    .build())
            .development(Opinion.<Set<FavorableNews.Development>>builder()
                    .choice(Set.of(FavorableNews.Development.재개발, FavorableNews.Development.재건축))
                    .text("favorableNews_development_text")
                    .build())
            .education(Opinion.<Set<FavorableNews.Education>>builder()
                    .choice(Set.of(FavorableNews.Education.초등학교_신설, FavorableNews.Education.중학교_신설))
                    .text("favorableNews_education_text")
                    .build())
            .environment(Opinion.<Set<FavorableNews.Environment>>builder()
                    .choice(Set.of(FavorableNews.Environment.하천_복원, FavorableNews.Environment.대형공원))
                    .text("favorableNews_environment_text")
                    .build())
            .culture(Opinion.<Set<FavorableNews.Culture>>builder()
                    .choice(Set.of(FavorableNews.Culture.대형병원, FavorableNews.Culture.문화센터))
                    .text("favorableNews_culture_text")
                    .build())
            .industry(Opinion.<Set<FavorableNews.Industry>>builder()
                    .choice(Set.of(FavorableNews.Industry.산업단지, FavorableNews.Industry.기업_이전))
                    .text("favorableNews_industry_text")
                    .build())
            .policy(Opinion.<Set<FavorableNews.Policy>>builder()
                    .choice(Set.of(FavorableNews.Policy.잘_모르겠어요))
                    .text("favorableNews_policy_text")
                    .build())
            .build();

    static Infra updatedInfra = Infra.builder()
            .transportation(Opinion.<Set<Infra.Transportation>>builder()
                    .choice(Set.of(Infra.Transportation.지하철역_가까움))
                    .text("infra_updated_transportation_text")
                    .build())
            .schoolDistrict(Opinion.<Set<Infra.SchoolDistrict>>builder()
                    .choice(Set.of(Infra.SchoolDistrict.초품아))
                    .text("infra_updated__schoolDistrict_text")
                    .build())
            .amenity(Opinion.<Set<Infra.Amenity>>builder()
                    .choice(Set.of(Infra.Amenity.미용실, Infra.Amenity.병원))
                    .text("infra_updated__amenity_text")
                    .build())
            .facility(Opinion.<Set<Infra.Facility>>builder()
                    .choice(Set.of(Infra.Facility.골프연습장, Infra.Facility.배드민턴장))
                    .text("infra_updated__facility_text")
                    .build())
            .surroundings(Opinion.<Set<Infra.Surroundings>>builder()
                    .choice(Set.of(Infra.Surroundings.성당, Infra.Surroundings.시장))
                    .text("infra_updated__surroundings_text")
                    .build())
            .landmark(Opinion.<Set<Infra.Landmark>>builder()
                    .choice(Set.of(Infra.Landmark.사찰, Infra.Landmark.박물관, Infra.Landmark.전망대))
                    .text("infra_updated__landmark_text")
                    .build())
            .unpleasantFacility(Opinion.<Set<Infra.UnpleasantFacility>>builder()
                    .choice(Set.of(Infra.UnpleasantFacility.고층_건물, Infra.UnpleasantFacility.산업단지))
                    .text("infra_updated__unpleasantFacility_text")
                    .build())
            .build();

    static ComplexEnvironment updatedComplexEnvironment = ComplexEnvironment.builder()
            .buildingCondition(Opinion.<ObjectiveItem>builder()
                    .choice(ObjectiveItem.확인이_어려워요)
                    .text("complexEnvironment_updated__buildingCondition_text")
                    .build())
            .security(Opinion.<ObjectiveItem>builder()
                    .choice(ObjectiveItem.최고예요)
                    .text("complexEnvironment_updated__security_text")
                    .build())
            .childrenFacility(Opinion.<ObjectiveItem>builder()
                    .choice(ObjectiveItem.좋아요)
                    .text("complexEnvironment_updated__childrenFacility_text")
                    .build())
            .seniorFacility(Opinion.<ObjectiveItem>builder()
                    .choice(ObjectiveItem.평범해요)
                    .text("complexEnvironment_updated__seniorFacility_text")
                    .build())
            .build();

    static ComplexFacility updatedComplexFacility = ComplexFacility.builder()
            .family(Opinion.<Set<ComplexFacility.Family>>builder()
                    .choice(Set.of(ComplexFacility.Family.어린이집))
                    .text("complexFacility_updated__family_text")
                    .build())
            .multipurpose(Opinion.<Set<ComplexFacility.Multipurpose>>builder()
                    .choice(Set.of(ComplexFacility.Multipurpose.공용빨래방))
                    .text("complexFacility_updated__multipurpose_text")
                    .build())
            .leisure(Opinion.<Set<ComplexFacility.Leisure>>builder()
                    .choice(Set.of(ComplexFacility.Leisure.공부방, ComplexFacility.Leisure.조식))
                    .text("complexFacility_updated__leisure_text")
                    .build())
            .surroundings(Opinion.<Set<ComplexFacility.Surroundings>>builder()
                    .choice(Set.of(ComplexFacility.Surroundings.잔디밭, ComplexFacility.Surroundings.조형물))
                    .text("complexFacility_updated__surroundings_text")
                    .build())
            .build();

    static FavorableNews updatedFavorableNews = FavorableNews.builder()
            .transportation(Opinion.<Set<FavorableNews.Transportation>>builder()
                    .choice(Set.of(FavorableNews.Transportation.교통_허브_지정, FavorableNews.Transportation.지하철_개통))
                    .text("favorableNews_updated__transportation_text")
                    .build())
            .development(Opinion.<Set<FavorableNews.Development>>builder()
                    .choice(Set.of(FavorableNews.Development.재개발, FavorableNews.Development.백화점))
                    .text("favorableNews_updated__development_text")
                    .build())
            .education(Opinion.<Set<FavorableNews.Education>>builder()
                    .choice(Set.of(FavorableNews.Education.초등학교_신설, FavorableNews.Education.고등학교_신설))
                    .text("favorableNews_updated__education_text")
                    .build())
            .environment(Opinion.<Set<FavorableNews.Environment>>builder()
                    .choice(Set.of(FavorableNews.Environment.호수_복원))
                    .text("favorableNews_updated__environment_text")
                    .build())
            .culture(Opinion.<Set<FavorableNews.Culture>>builder()
                    .choice(Set.of(FavorableNews.Culture.문화센터))
                    .text("favorableNews_updated__culture_text")
                    .build())
            .industry(Opinion.<Set<FavorableNews.Industry>>builder()
                    .choice(Set.of(FavorableNews.Industry.산업단지))
                    .text("favorableNews_updated__industry_text")
                    .build())
            .policy(Opinion.<Set<FavorableNews.Policy>>builder()
                    .choice(Set.of(FavorableNews.Policy.잘_모르겠어요))
                    .text("favorableNews_updated__policy_text")
                    .build())
            .build();
}
