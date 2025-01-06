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
public class FavorableNews {

    // 교통
    private Opinion<Set<Transportation>> transportation;
    // 개발
    private Opinion<Set<Development>> development;
    // 교육
    private Opinion<Set<Education>> education;
    // 자연환경
    private Opinion<Set<Environment>> environment;
    // 문화
    private Opinion<Set<Culture>> culture;
    // 산업
    private Opinion<Set<Industry>> industry;
    // 정책
    private Opinion<Set<Policy>> policy;

    public enum Transportation {
        잘_모르겠어요, 지하철_개통, 고속철도역_신설, 교통_허브_지정
    }

    public enum Development {
        잘_모르겠어요, 재개발, 재건축, 리모델링, 인근_신도시_개발, 복합단지_개발, 대형_쇼핑몰, 백화점, 대형_오피스_단지
    }

    public enum Education {
        잘_모르겠어요, 초등학교_신설, 중학교_신설, 고등학교_신설, 특목고, 자사고, 국제학교, 대학_캠퍼스
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
        잘_모르겠어요, 투기과열지구_해제, 규제_완화, 특구_지정, 일자리_창출_정책
    }
}
