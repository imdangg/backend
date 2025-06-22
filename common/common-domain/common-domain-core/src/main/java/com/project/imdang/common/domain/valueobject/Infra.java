package com.project.imdang.common.domain.valueobject;

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
    private Set<Transportation> transportations;
    // 학군
    private Set<SchoolDistrict> schoolDistricts;
    // 생활 편의시설
    private Set<Amenity> amenities;
    // 문화 및 여가시설(단지 외부)
    private Set<Facility> facilities;
    // 주변 환경
    private Set<Surroundings> surroundings;

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
        해당_없음, 강, 산, 공원, 산책로, 식당가, 시장
    }
}
