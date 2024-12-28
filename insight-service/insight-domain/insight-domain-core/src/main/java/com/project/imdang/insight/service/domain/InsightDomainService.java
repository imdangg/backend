package com.project.imdang.insight.service.domain;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.entity.Snapshot;
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

public interface InsightDomainService {
//    Insight validateAndEvaluateInsight(Insight insight);
    Insight createInsight(Insight insight);
    InsightUpdatedEvent updateInsight(Insight insight, MemberId updatedBy, int score,
                                      String title, String contents, String mainImage, String summary,
                                      ZonedDateTime visitAt, VisitMethod visitMethod, Access access,
                                      Infra infra, ComplexEnvironment complexEnvironment, ComplexFacility complexFacility, FavorableNews favorableNews);
    InsightDeletedEvent deleteInsight(Insight insight, MemberId deletedBy);
    Insight recommendInsight(Insight insight);
    InsightAccusedEvent accuseInsight(Insight insight, MemberId accusedBy);
//    InsightRequestedEvent requestInsight(Insight insight);

    // TODO - CHECK
    Snapshot captureInsight(Insight insight);
}
