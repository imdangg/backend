package com.project.imdang.insight.service.application.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.domain.jwt.JwtTokenProvider;
import com.project.imdang.insight.service.domain.InsightServiceApplication;
import com.project.imdang.insight.service.domain.dto.insight.accuse.AccuseInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.create.CreateInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.delete.DeleteInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.recommend.RecommendInsightCommand;
import com.project.imdang.insight.service.domain.dto.insight.request.RequestInsightCommand;
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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = InsightServiceApplication.class)
class InsightControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Test
    void list() throws Exception {
        mockMvc.perform(get("/insights")
//                        .param("pageNumber", "0")
//                        .param("pageSize", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void listByApartmentComplex() throws Exception {
        mockMvc.perform(get("/insights/by-apartment-complex")
                        .param("apartmentComplexName", "신논현 더 센트럴 푸르지오")
//                        .param("pageNumber", "0")
//                        .param("pageSize", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andDo(print())
                .andReturn();
    }

    @Test
    void detail() throws Exception {
        mockMvc.perform(get("/insights/detail")
                        .param("insightId", "1957eb5f-8554-4ce4-8e70-7231725f1e02")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }
/*
    @Test
    void preview() throws Exception {
        mockMvc.perform(get("/insights/preview")
                        .param("insightId", "1957eb5f-8554-4ce4-8e70-7231725f1e02")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }*/

//    @Test
//    void validateAndEvaluateInsight() {
//    }

    @Test
    void createInsight() throws Exception {

        // given
        UUID memberId = UUID.randomUUID();
        Address address = Address.builder()
                .siGunGu("강남구")
                .dong("신논현동")
                .build();
        ApartmentComplex apartmentComplex = ApartmentComplex.builder()
                .name("신논현 더 센트럴 푸르지오")
                .build();

        Infra infra = Infra.builder()
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

        ComplexEnvironment complexEnvironment = ComplexEnvironment.builder()
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

        ComplexFacility complexFacility = ComplexFacility.builder()
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

        FavorableNews favorableNews = FavorableNews.builder()
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

        CreateInsightCommand createInsightCommand = CreateInsightCommand.builder()
                .memberId(memberId)
                .score(80)
                .address(address)
                .apartmentComplex(apartmentComplex)
                .title("title")
                .contents("contents")
                .mainImage("image1")
                .summary("summary")
                .visitAt(ZonedDateTime.now().minusDays(3))
                .visitMethod(VisitMethod.대중교통)
                .access(Access.자유로움)
                .infra(infra)
                .complexEnvironment(complexEnvironment)
                .complexFacility(complexFacility)
                .favorableNews(favorableNews)
                .build();

        // when
        // then
        mockMvc.perform(post("/insights/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createInsightCommand))) // 요청 본문 직렬화
                .andExpect(status().isOk()) // HTTP 상태 확인
                .andExpect(jsonPath("$.insightId", notNullValue()));
    }

    @Test
    void updateInsight() throws Exception {

        // given
        Infra infra = Infra.builder()
                .transportation(Opinion.<Set<Infra.Transportation>>builder()
                        .choice(Set.of(Infra.Transportation.버스정류장_가까움, Infra.Transportation.주차_편리))
                        .text("infra_updated_transportation_text")
                        .build())
                .schoolDistrict(Opinion.<Set<Infra.SchoolDistrict>>builder()
                        .choice(Set.of(Infra.SchoolDistrict.고등학교))
                        .text("infra_updated__schoolDistrict_text")
                        .build())
                .amenity(Opinion.<Set<Infra.Amenity>>builder()
                        .choice(Set.of(Infra.Amenity.병원))
                        .text("infra_updated__amenity_text")
                        .build())
                .facility(Opinion.<Set<Infra.Facility>>builder()
                        .choice(Set.of(Infra.Facility.도서관, Infra.Facility.수영장))
                        .text("infra_updated__facility_text")
                        .build())
                .surroundings(Opinion.<Set<Infra.Surroundings>>builder()
                        .choice(Set.of(Infra.Surroundings.강, Infra.Surroundings.교회))
                        .text("infra_updated__surroundings_text")
                        .build())
                .landmark(Opinion.<Set<Infra.Landmark>>builder()
                        .choice(Set.of(Infra.Landmark.고궁, Infra.Landmark.사찰))
                        .text("infra_updated__landmark_text")
                        .build())
                .unpleasantFacility(Opinion.<Set<Infra.UnpleasantFacility>>builder()
                        .choice(Set.of(Infra.UnpleasantFacility.고속도로, Infra.UnpleasantFacility.철도))
                        .text("infra_updated__unpleasantFacility_text")
                        .build())
                .build();

        ComplexEnvironment complexEnvironment = ComplexEnvironment.builder()
                .buildingCondition(Opinion.<ObjectiveItem>builder()
                        .choice(ObjectiveItem.좋아요)
                        .text("complexEnvironment_updated__buildingCondition_text")
                        .build())
                .security(Opinion.<ObjectiveItem>builder()
                        .choice(ObjectiveItem.별로예요)
                        .text("complexEnvironment_updated__security_text")
                        .build())
                .childrenFacility(Opinion.<ObjectiveItem>builder()
                        .choice(ObjectiveItem.최고예요)
                        .text("complexEnvironment_updated__childrenFacility_text")
                        .build())
                .seniorFacility(Opinion.<ObjectiveItem>builder()
                        .choice(ObjectiveItem.확인이_어려워요)
                        .text("complexEnvironment_updated__seniorFacility_text")
                        .build())
                .build();

        ComplexFacility complexFacility = ComplexFacility.builder()
                .family(Opinion.<Set<ComplexFacility.Family>>builder()
                        .choice(Set.of(ComplexFacility.Family.경로당))
                        .text("complexFacility_updated__family_text")
                        .build())
                .multipurpose(Opinion.<Set<ComplexFacility.Multipurpose>>builder()
                        .choice(Set.of(ComplexFacility.Multipurpose.다목적실))
                        .text("complexFacility_updated__multipurpose_text")
                        .build())
                .leisure(Opinion.<Set<ComplexFacility.Leisure>>builder()
                        .choice(Set.of(ComplexFacility.Leisure.독서실, ComplexFacility.Leisure.조식))
                        .text("complexFacility_updated__leisure_text")
                        .build())
                .surroundings(Opinion.<Set<ComplexFacility.Surroundings>>builder()
                        .choice(Set.of(ComplexFacility.Surroundings.분수, ComplexFacility.Surroundings.벤치))
                        .text("complexFacility_updated__surroundings_text")
                        .build())
                .build();

        FavorableNews favorableNews = FavorableNews.builder()
                .transportation(Opinion.<Set<FavorableNews.Transportation>>builder()
                        .choice(Set.of(FavorableNews.Transportation.고속철도역_신설, FavorableNews.Transportation.지하철_개통))
                        .text("favorableNews_updated__transportation_text")
                        .build())
                .development(Opinion.<Set<FavorableNews.Development>>builder()
                        .choice(Set.of(FavorableNews.Development.재개발, FavorableNews.Development.재건축))
                        .text("favorableNews_updated__development_text")
                        .build())
                .education(Opinion.<Set<FavorableNews.Education>>builder()
                        .choice(Set.of(FavorableNews.Education.초등학교_신설, FavorableNews.Education.중학교_신설))
                        .text("favorableNews_updated__education_text")
                        .build())
                .environment(Opinion.<Set<FavorableNews.Environment>>builder()
                        .choice(Set.of(FavorableNews.Environment.하천_복원, FavorableNews.Environment.대형공원))
                        .text("favorableNews_updated__environment_text")
                        .build())
                .culture(Opinion.<Set<FavorableNews.Culture>>builder()
                        .choice(Set.of(FavorableNews.Culture.대형병원, FavorableNews.Culture.문화센터))
                        .text("favorableNews_updated__culture_text")
                        .build())
                .industry(Opinion.<Set<FavorableNews.Industry>>builder()
                        .choice(Set.of(FavorableNews.Industry.산업단지, FavorableNews.Industry.기업_이전))
                        .text("favorableNews_updated__industry_text")
                        .build())
                .policy(Opinion.<Set<FavorableNews.Policy>>builder()
                        .choice(Set.of(FavorableNews.Policy.잘_모르겠어요))
                        .text("favorableNews_updated__policy_text")
                        .build())
                .build();

        UpdateInsightCommand updateInsightCommand = UpdateInsightCommand.builder()
                .insightId(UUID.fromString("1957eb5f-8554-4ce4-8e70-7231725f1e02"))
                .score(95)
                .title("updated_title")
                .contents("updated_contents")
                .mainImage("updated_image1")
                .summary("updated_summary")
                .visitAt(ZonedDateTime.now().minusDays(3))
                .visitMethod(VisitMethod.대중교통)
                .access(Access.자유로움)
                .infra(infra)
                .complexEnvironment(complexEnvironment)
                .complexFacility(complexFacility)
                .favorableNews(favorableNews)
                .build();

        // TODO - QUERY CHECK
        // Hibernate: update insight set access=?,accused_count=?,dong=?,si_gun_gu=?,name=?,complex_environment=?,complex_facility=?,contents=?,created_at=?,favorable_news=?,infra=?,main_image=?,member_id=?,recommended_count=?,score=?,summary=?,title=?,view_count=?,visit_at=?,visit_method=? where id=?
        // Hibernate: update snapshot set access=?,dong=?,si_gun_gu=?,name=?,complex_environment=?,complex_facility=?,contents=?,favorable_news=?,infra=?,main_image=?,member_id=?,summary=?,title=?,visit_at=?,visit_method=? where id=?
        // when
        // then
        mockMvc.perform(post("/insights/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateInsightCommand))) // 요청 본문 직렬화
                .andExpect(status().isOk()) // HTTP 상태 확인
                .andExpect(jsonPath("$.insightId", notNullValue()));
    }

    @Test
    void deleteInsight() throws Exception {
        DeleteInsightCommand deleteInsightCommand = DeleteInsightCommand.builder()
                .insightId(UUID.fromString("1957eb5f-8554-4ce4-8e70-7231725f1e02"))
                .build();
        mockMvc.perform(post("/insights/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deleteInsightCommand))) // 요청 본문 직렬화
                .andExpect(status().isOk()) // HTTP 상태 확인
                .andExpect(jsonPath("$.insightId", notNullValue()));
    }

    @Test
    void recommendInsight() throws Exception {
        RecommendInsightCommand recommendInsightCommand = RecommendInsightCommand.builder()
                .insightId(UUID.fromString("1957eb5f-8554-4ce4-8e70-7231725f1e02"))
                .build();
        mockMvc.perform(post("/insights/recommend")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(recommendInsightCommand))) // 요청 본문 직렬화
                .andExpect(status().isOk()) // HTTP 상태 확인
                .andExpect(jsonPath("$.insightId", notNullValue()));
    }

    @Test
    void accuseInsight() throws Exception {
        AccuseInsightCommand accuseInsightCommand = AccuseInsightCommand.builder()
                .insightId(UUID.fromString("1957eb5f-8554-4ce4-8e70-7231725f1e02"))
                .accuseMemberId(UUID.randomUUID())
                .build();
        mockMvc.perform(post("/insights/accuse")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accuseInsightCommand))) // 요청 본문 직렬화
                .andExpect(status().isOk()) // HTTP 상태 확인
                .andExpect(jsonPath("$.insightId", notNullValue()));
    }

    @Test
    void requestInsight() throws Exception {
        RequestInsightCommand requestInsightCommand = RequestInsightCommand.builder()
                .requestedInsightId(UUID.fromString("1957eb5f-8554-4ce4-8e70-7231725f1e02"))
                .requestMemberId(UUID.randomUUID())
                .memberCouponId(1L)
                .build();
        mockMvc.perform(post("/insights/request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestInsightCommand))) // 요청 본문 직렬화
                .andExpect(status().isOk()) // HTTP 상태 확인
                .andExpect(jsonPath("$.insightId", notNullValue()));
    }
}
