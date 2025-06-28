package com.project.imdang.insight.domain.dto.insight.detail;

import com.project.imdang.common.domain.valueobject.Access;
import com.project.imdang.common.domain.valueobject.Address;
import com.project.imdang.common.domain.valueobject.ApartmentComplex;
import com.project.imdang.common.domain.valueobject.ComplexEnvironment;
import com.project.imdang.common.domain.valueobject.Infra;
import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.common.domain.valueobject.VisitMethod;
import com.project.imdang.common.domain.valueobject.VisitTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Set;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InsightDetailResult {

    private MemberId memberId;      //작성자 MemberID
    private String memberNickname;  //작성자 닉네임

    private InsightId insightId;    //인사이트ID

    private String mainImage;       //메인 이미지
    //TODO : 추가 이미지
    private String title;           //제목

    private Address address;    //단지 주소
    private ApartmentComplex apartmentComplex;  //단지 이름

    private LocalDate visitAt; //방문일자
    private Set<VisitTime> visitTimes;  //방문 시간대
    private Set<VisitMethod> visitMethods; //방문 교통 수단
    private Access access;  //출입 제한
    private String summary; //인사이트 요약

    private Infra infra; //인프라
    private ComplexEnvironment complexEnvironment; //단지 환경

    private Boolean recommended;        // 로그인한 사용자가 추천했는가?
    private Boolean accused;            // 로그인한 사용자가 신고했는가?
    private Long recommendedCount;   //추천수
    private Long accusedCount;       //신고수
    private Long viewCount;          //조회수

//    private Integer score;
    private ZonedDateTime createdAt;
    private Boolean createdByMe;
}
