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
public class ComplexFacility {
    // 가족
    @Schema(description = "가족")
    @NotNull
    private Opinion<Set<Family>> family;
    // 다목적
    @Schema(description = "다목적")
    @NotNull
    private Opinion<Set<Multipurpose>> multipurpose;
    // 여가(단지 내부)
    @Schema(description = "여가(단지 내부)")
    @NotNull
    private Opinion<Set<Leisure>> leisure;
    // 환경
    @Schema(description = "환경")
    @NotNull
    private Opinion<Set<Surroundings>> surroundings;

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
