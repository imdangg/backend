package com.project.imdang.insight.service.domain.dto.insight.create;

import com.project.imdang.insight.service.domain.valueobject.Access;
import com.project.imdang.insight.service.domain.valueobject.Address;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.domain.valueobject.ComplexEnvironment;
import com.project.imdang.insight.service.domain.valueobject.ComplexFacility;
import com.project.imdang.insight.service.domain.valueobject.FavorableNews;
import com.project.imdang.insight.service.domain.valueobject.Infra;
import com.project.imdang.insight.service.domain.valueobject.VisitMethod;
import com.project.imdang.insight.service.domain.valueobject.VisitTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateInsightCommand {
    // TODO : 모든 요소 필수값
    // TODO : 최소 1글자 최대 20글자

    @Setter
    private UUID memberId;  // createdBy
    private int score;

    @Setter
    private MultipartFile mainImage;
    private String title;

    private Address address;
    private ApartmentComplex apartmentComplex;

    // TODO - ZoneId 필요
//    private ZonedDateTime visitAt;
    private LocalDate visitAt;
    private Set<VisitTime> visitTimes;
    private Set<VisitMethod> visitMethods;
    private Access access;

    private String summary;

    // 인프라
    private Infra infra;
    // 단지 환경
    private ComplexEnvironment complexEnvironment;
    // 단지 시설
    private ComplexFacility complexFacility;
    // (예정된) 호재
    private FavorableNews favorableNews;
}
