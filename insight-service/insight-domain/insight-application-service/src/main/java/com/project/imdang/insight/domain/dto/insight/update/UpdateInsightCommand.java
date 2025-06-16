package com.project.imdang.insight.domain.dto.insight.update;

import com.project.imdang.common.domain.valueobject.Access;
import com.project.imdang.common.domain.valueobject.Address;
import com.project.imdang.common.domain.valueobject.ApartmentComplex;
import com.project.imdang.common.domain.valueobject.ComplexEnvironment;
import com.project.imdang.common.domain.valueobject.File;
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
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateInsightCommand {
    private InsightId insightId;
    private MemberId memberId;

    private Address address;
    private ApartmentComplex apartmentComplex;

    private int score;
    private String title;
    private List<File> mainImage;
    private String summary;

    private LocalDate visitAt;
    private Set<VisitTime> visitTimes;
    private Set<VisitMethod> visitMethods;
    private Access access;

    // 인프라
    private Infra infra;
    // 단지 환경
    private ComplexEnvironment complexEnvironment;
}
