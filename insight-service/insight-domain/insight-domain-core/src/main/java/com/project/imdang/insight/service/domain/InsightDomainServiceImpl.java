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
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

@Slf4j
public class InsightDomainServiceImpl implements InsightDomainService {
// TODO - CHECK : 왜 DomainServiceImpl을 bean으로 등록하는가?

    @Override
    public Insight validateAndEvaluateInsight(Insight insight) {
        insight.validateAndEvaluate();
        log.info("Insight[id: {}] is validated.", insight.getId().getValue());
        return insight;
    }

    @Override
    public Insight createInsight(Insight insight) {
        insight.initialize();
        log.info("Insight[id: {}] is created.", insight.getId().getValue());
        return insight;
    }

    @Override
    public InsightUpdatedEvent updateInsight(Insight insight, int score,
                                             String title, String contents, Set<String> images, String summary,
                                             ZonedDateTime visitAt, VisitMethod visitMethod, Access access,
                                             Infra infra, ComplexEnvironment complexEnvironment, ComplexFacility complexFacility, FavorableNews favorableNews) {
        insight.update(score, title, contents, images, summary, visitAt, visitMethod, access, infra, complexEnvironment, complexFacility, favorableNews);
        log.info("Insight[id: {}] is updated.", insight.getId().getValue());
        return new InsightUpdatedEvent(insight, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public InsightDeletedEvent deleteInsight(Insight insight) {
//        insight.delete();
        log.info("Insight[id: {}] is deleted.", insight.getId().getValue());
        return new InsightDeletedEvent(insight, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public Insight recommendInsight(Insight insight) {
        insight.recommend();
        log.info("Insight[id: {}] is recommended.", insight.getId().getValue());
        return insight;
    }

    @Override
    public InsightAccusedEvent accuseInsight(Insight insight) {
        insight.accuse();
        log.info("Insight[id: {}] is accused.", insight.getId().getValue());
        return new InsightAccusedEvent(insight, ZonedDateTime.now(ZoneId.of("UTC")));
    }
}
