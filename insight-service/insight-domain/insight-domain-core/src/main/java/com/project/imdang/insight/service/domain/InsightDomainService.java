package com.project.imdang.insight.service.domain;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.event.InsightAccusedEvent;
import com.project.imdang.insight.service.domain.event.InsightDeletedEvent;
import com.project.imdang.insight.service.domain.event.InsightRecommendedEvent;
import com.project.imdang.insight.service.domain.event.InsightUpdatedEvent;
import com.project.imdang.insight.service.domain.valueobject.Access;
import com.project.imdang.insight.service.domain.valueobject.Address;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.domain.valueobject.ComplexEnvironment;
import com.project.imdang.insight.service.domain.valueobject.ComplexFacility;
import com.project.imdang.insight.service.domain.valueobject.FavorableNews;
import com.project.imdang.insight.service.domain.valueobject.Infra;
import com.project.imdang.insight.service.domain.valueobject.VisitMethod;
import com.project.imdang.insight.service.domain.valueobject.VisitTime;

import java.time.LocalDate;
import java.util.Set;

public interface InsightDomainService {
    Insight createInsight(Insight insight, String mainImage);
    InsightUpdatedEvent updateInsight(Insight insight,
                                      MemberId memberId,
                                      String mainImage,
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
                                      ComplexFacility complexFacility,
                                      FavorableNews favorableNews,
                                      int score);
    InsightDeletedEvent deleteInsight(Insight insight, MemberId deletedBy);
    InsightRecommendedEvent recommendInsight(Insight insight, MemberId recommendedBy);
    InsightAccusedEvent accuseInsight(Insight insight, MemberId accusedBy);
//    InsightRequestedEvent requestInsight(Insight insight);

    void viewInsight(Insight insight);

    // TODO - CHECK
    Snapshot captureInsight(Insight insight);
}
