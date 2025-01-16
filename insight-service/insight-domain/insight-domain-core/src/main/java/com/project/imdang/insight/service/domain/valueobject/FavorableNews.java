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
public class FavorableNews {

    // 교통
    @Schema(description = "교통")
    @NotNull
    private Set<Transportation> transportations;
    // 개발
    @Schema(description = "개발")
    @NotNull
    private Set<Development> developments;
    // 교육
    @Schema(description = "교육")
    @NotNull
    private Set<Education> educations;
    // 자연환경
    @Schema(description = "자연 환경")
    @NotNull
    private Set<Environment> environments;
    // 문화
    @Schema(description = "문화")
    @NotNull
    private Set<Culture> cultures;
    // 산업
    @Schema(description = "산업")
    @NotNull
    private Set<Industry> industries;
    // 정책
    @Schema(description = "정책")
    @NotNull
    private Set<Policy> policies;
    private String text;

    public enum Transportation {
        잘_모르겠어요, 지하철_개통, 고속철도역_신설, 교통_허브_지정
    }

    public enum Development {
        잘_모르겠어요, 재개발, 재건축, 리모델링, 인근_신도시_개발, 복합단지_개발, 대형_쇼핑몰, 백화점, 대형_오피스_단지
    }

    public enum Education {
        잘_모르겠어요, 초등학교_신설_예정, 고등학교_신설_예정, 특목고, 자사고, 국제학교, 대학_캠퍼스
    }

    public enum Environment {
        잘_모르겠어요, 대형공원, 하천_복원, 호수_복원
    }

    public enum Culture {
        잘_모르겠어요, 대형병원, 문화센터, 도서관, 공연장, 체육관
    }

    public enum Industry {
        잘_모르겠어요, 산업단지, 기업_이전, 스타트업_단지
    }

    public enum Policy {
        잘_모르겠어요, 투기_과열_지구_해제, 규제_완화, 특구_지정, 일자리_창출_정책
    }
}
