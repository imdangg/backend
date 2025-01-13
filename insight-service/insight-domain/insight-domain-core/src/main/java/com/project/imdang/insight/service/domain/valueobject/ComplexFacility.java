package com.project.imdang.insight.service.domain.valueobject;

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
public class ComplexFacility {
    // 가족
    private Set<FamilyFacility> familyFacilities;
    // 다목적
    private Set<MultipurposeFacility> multipurposeFacilities;
    // 여가(단지 내부)
    private Set<LeisureFacility> leisureFacilities;
    // 환경
    private Set<Surroundings> surroundings;
    private String text;

    // TODO - CHECK : DB
    public enum FamilyFacility {
        해당_없음, 어린이집, 경로당
    }

    public enum MultipurposeFacility {
        해당_없음, 다목적실, 입주자_대표_회의실, 공용_세탁소, 공용_휴게실
    }

    public enum LeisureFacility {
        해당_없음, 피트니스_센터, 독서실, 사우나, 목욕탕, 스크린_골프장, 영화관, 도서관, 수영장, 공부방, 어린이집, 게스트하우스, 워터파크, 조식
    }

    public enum Surroundings {
        해당_없음, 잔디밭, 조형물, 벤치, 테이블_및_의자, 분수
    }
}
