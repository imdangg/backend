package com.project.imdang.insight.service.domain.dto;

import com.project.imdang.insight.service.domain.valueobject.Access;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.domain.valueobject.VisitMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.Set;

@Builder
@Getter
@AllArgsConstructor
public class CreateInsightCommand {
    // TODO : 모든 요소 필수값
    // TODO : 최소 1글자 최대 20글자
    private final String title;
    private final String image;
    // TODO
    private final ApartmentComplex apartmentComplex;
    private final ZonedDateTime visitAt;
    private final Set<VisitMethod> visitMethods;
    private final Access access;

    private final String contents;
    private final String summary;

    // 교통
//    private final Set<Transportation> transportations;
//    private final String transportationOpinion;
    // 학군
//    private final Set<SchoolDistrict> schoolDistricts;
//    private final String schoolDistrictOpinion;
}
