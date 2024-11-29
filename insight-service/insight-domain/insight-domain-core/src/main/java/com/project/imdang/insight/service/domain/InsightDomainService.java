package com.project.imdang.insight.service.domain;

import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.event.InsightAccusedEvent;
import com.project.imdang.insight.service.domain.event.InsightDeletedEvent;
import com.project.imdang.insight.service.domain.event.InsightUpdatedEvent;
import com.project.imdang.insight.service.domain.valueobject.Access;
import com.project.imdang.insight.service.domain.valueobject.ComplexEnvironment;
import com.project.imdang.insight.service.domain.valueobject.ComplexFacility;
import com.project.imdang.insight.service.domain.valueobject.FavorableNews;
import com.project.imdang.insight.service.domain.valueobject.Infra;
import com.project.imdang.insight.service.domain.valueobject.VisitMethod;

import java.time.ZonedDateTime;
import java.util.Set;

public interface InsightDomainService {
    Insight validateAndEvaluateInsight(Insight insight);
    Insight createInsight(Insight insight);
    InsightUpdatedEvent updateInsight(Insight insight, int score,
                                      String title, String contents, Set<String> images, String summary,
                                      ZonedDateTime visitAt, VisitMethod visitMethod, Access access,
                                      Infra infra, ComplexEnvironment complexEnvironment, ComplexFacility complexFacility, FavorableNews favorableNews);
    InsightDeletedEvent deleteInsight(Insight insight);
    Insight recommendInsight(Insight insight);
    InsightAccusedEvent accuseInsight(Insight insight);
//    InsightRequestedEvent requestInsight(Insight insight);
}
