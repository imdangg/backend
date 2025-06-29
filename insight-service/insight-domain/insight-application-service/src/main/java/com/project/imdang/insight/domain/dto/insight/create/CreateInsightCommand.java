package com.project.imdang.insight.domain.dto.insight.create;

import com.project.imdang.common.domain.valueobject.Access;
import com.project.imdang.common.domain.valueobject.Address;
import com.project.imdang.common.domain.valueobject.ApartmentComplex;
import com.project.imdang.common.domain.valueobject.ComplexEnvironment;
import com.project.imdang.common.domain.valueobject.File;
import com.project.imdang.common.domain.valueobject.Infra;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.common.domain.valueobject.VisitMethod;
import com.project.imdang.common.domain.valueobject.VisitTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateInsightCommand {
    private MemberId memberId; // createdBy
    private List<File> images;

    private String title;
    private Address address;
    private ApartmentComplex apartmentComplex;

    private LocalDate visitAt;
    private Set<VisitTime> visitTimes;
    private Set<VisitMethod> visitMethods;

    private String summary;
    private Access access;

    // 인프라
    private Infra infra;
    // 단지 환경
    private ComplexEnvironment complexEnvironment;
}
