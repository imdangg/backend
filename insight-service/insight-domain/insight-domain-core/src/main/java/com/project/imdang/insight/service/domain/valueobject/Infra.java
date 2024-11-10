package com.project.imdang.insight.service.domain.valueobject;

import java.util.Set;

public class Infra {

    // 교통
    private final Opinion<Set<Transportation>> transportation;
    // 학군
    private final Opinion<Set<SchoolDistrict>> schoolDistrict;
    // 생활 편의시설
    private final Opinion<Set<Amenity>> amenity;
    // 문화 및 여가시설(단지 외부)
    private final Opinion<Set<Facility>> facility;
    // 주변 환경
    private final Opinion<Set<Surroundings>> surroundings;
    // 랜드마크
    private final Opinion<Set<Landmark>> landmark;
    // 기피시설
    private final Opinion<Set<UnpleasantFacility>> unpleasantFacility;

    public Infra(Opinion<Set<Transportation>> transportation, Opinion<Set<SchoolDistrict>> schoolDistrict, Opinion<Set<Amenity>> amenity, Opinion<Set<Facility>> facility, Opinion<Set<Surroundings>> surroundings, Opinion<Set<Landmark>> landmark, Opinion<Set<UnpleasantFacility>> unpleasantFacility) {
        this.transportation = transportation;
        this.schoolDistrict = schoolDistrict;
        this.amenity = amenity;
        this.facility = facility;
        this.surroundings = surroundings;
        this.landmark = landmark;
        this.unpleasantFacility = unpleasantFacility;
    }

    public enum Transportation {
        주차_편리, 지하철역_가까움, 버스정류장_가까움
    }

    public enum SchoolDistrict {
        초품아, 어린이집, 중학교, 고등학교, 학원가
    }

    public enum Amenity {
        주민센터, 편의점, 소형마트, 대형마트, 병원, 은행, 카페, 미용실, 약국, 우체국
    }

    public enum Facility {
        도서관, 영화관, 체육관, 헬스장, 수영장, 배드민턴장, 테니스장, 골프연습장
    }

    public enum Surroundings {
        강, 바다, 산, 공원, 산책로, 교회, 성당, 식당가, 시장
    }

    public enum Landmark {
        놀이공원, 복합쇼핑몰, 고궁, 전망대, 국립공원, 한옥마을, 사찰, 미술관, 박물관
    }

    public enum UnpleasantFacility {
        고속도로, 철도, 유흥거리, 산업단지, 공장, 쓰레기_소각장, 고층_건물, 공사중_건물
    }
}
