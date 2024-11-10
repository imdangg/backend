package com.project.imdang.insight.service.domain.valueobject;

import java.util.Set;

public class ComplexFacility {
    // 가족
    private final Opinion<Set<Family>> family;
    // 다목적
    private final Opinion<Set<Multipurpose>> multipurpose;
    // 여가(단지 내부)
    private final Opinion<Set<Leisure>> leisure;
    // 환경
    private final Opinion<Surroundings> surroundings;

    public ComplexFacility(Opinion<Set<Family>> family, Opinion<Set<Multipurpose>> multipurpose, Opinion<Set<Leisure>> leisure, Opinion<Surroundings> surroundings) {
        this.family = family;
        this.multipurpose = multipurpose;
        this.leisure = leisure;
        this.surroundings = surroundings;
    }

    // TODO - CHECK : DB
    public enum Family {
        어린이집, 경로당
    }

    public enum Multipurpose {
        다목적실, 입주자대표회의실, 공용빨래방, 공용휴게실
    }

    public enum Leisure {
        피트니스_센터, 독서실, 사우나, 목욕탕, 스크린골프장, 영화관, 도서관, 수영장, 공부방, 어린이집, 게스트하우스, 워터파크, 조식
    }

    public enum Surroundings {
        잔디밭, 조형물, 벤치, 테이블_및_의자, 분수
    }
}
