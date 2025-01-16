package com.project.imdang.insight.service.domain.valueobject;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Infra {

    // 교통
    @Schema(description = "교통")
    @NotNull
    private Set<Transportation> transportations;
    // 학군
    @Schema(description = "학군")
    @NotNull
    private Set<SchoolDistrict> schoolDistricts;
    // 생활 편의시설
    @Schema(description = "생활 편의 시설")
    @NotNull
    private Set<Amenity> amenities;
    // 문화 및 여가시설(단지 외부)
    @Schema(description = "문화 및 여가 시설")
    @NotNull
    private Set<Facility> facilities;
    // 주변 환경
    @Schema(description = "주변 환경")
    @NotNull
    private Set<Surroundings> surroundings;
    // 랜드마크
    @Schema(description = "랜드 마크")
    @NotNull
    private Set<Landmark> landmarks;
    // 기피시설
    @Schema(description = "기피 시설")
    @NotNull
    private Set<UnpleasantFacility> unpleasantFacilities;
    @Schema(description = "인프라 총평")
    private String text;

    public enum Transportation {
        해당_없음, 주차_편리, 지하철역_주변, 버스_정류장_주변
    }

    public enum SchoolDistrict {
        해당_없음, 초품아, 어린이집, 중학교, 고등학교, 학원가
    }

    public enum Amenity {
        해당_없음, 주민센터, 편의점, 소형마트, 대형마트, 병원, 은행, 카페, 미용실, 약국, 우체국
    }

    public enum Facility {
        해당_없음, 도서관, 영화관, 체육관, 헬스장, 수영장, 배드민턴장, 테니스장, 골프연습장
    }

    public enum Surroundings {
        해당_없음, 강, 바다, 산, 공원, 산책로, 교회, 성당, 식당가, 시장
    }

    public enum Landmark {
        해당_없음, 놀이공원, 복합_쇼핑몰, 고궁, 전망대, 국립공원, 한옥마을, 사찰, 미술관, 박물관
    }

    public enum UnpleasantFacility {
        해당_없음, 고속도로, 철도, 유흥거리, 산업단지, 공장, 쓰레기_소각장, 고층_건물, 공사중_건물
    }
}
