package com.project.imdang.insight.domain;

import com.project.imdang.common.domain.valueobject.Access;
import com.project.imdang.common.domain.valueobject.Address;
import com.project.imdang.common.domain.valueobject.ApartmentComplex;
import com.project.imdang.common.domain.valueobject.ComplexEnvironment;
import com.project.imdang.common.domain.valueobject.Infra;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.common.domain.valueobject.VisitMethod;
import com.project.imdang.common.domain.valueobject.VisitTime;
import com.project.imdang.insight.domain.entity.Insight;
import com.project.imdang.insight.domain.event.InsightAccusedEvent;
import com.project.imdang.insight.domain.event.InsightDeletedEvent;
import com.project.imdang.insight.domain.event.InsightRecommendedEvent;
import com.project.imdang.insight.domain.event.InsightUpdatedEvent;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface InsightDomainService {
    Insight createInsight(Insight insight, List<String> uploadImages);
    InsightUpdatedEvent updateInsight(Insight insight,
                                      MemberId memberId,
                                      List<String> mainImage,
                                      String title,
                                      Address address,
                                      ApartmentComplex apartmentComplex,
                                      LocalDate visitAt,
                                      Set<VisitTime> visitTimes,
                                      Set<VisitMethod> visitMethods,
                                      Access access,
                                      String summary,
                                      Infra infra,
                                      ComplexEnvironment complexEnvironment,
                                      int score);
    InsightDeletedEvent deleteInsight(Insight insight, MemberId deletedBy);
    InsightRecommendedEvent recommendInsight(Insight insight, MemberId recommendedBy);
    InsightAccusedEvent accuseInsight(Insight insight, MemberId accusedBy);

    void viewInsight(Insight insight);
}
