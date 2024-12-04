package com.project.imdang.insight.service.domain;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.Accuse;
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
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

import static com.project.imdang.insight.service.domain.entity.Snapshot.createNewSnapshot;

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
    public InsightAccusedEvent accuseInsight(Insight insight, MemberId accusedBy) {
        insight.accuse();
        log.info("Insight[id: {}] is accused.", insight.getId().getValue());
        Accuse accuse = Accuse.createNewAccuse(accusedBy, insight.getMemberId());
        // TODO - CHECK : accuse.getCreatedAt()
        return new InsightAccusedEvent(insight, accuse, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public Snapshot captureInsight(Insight insight) {
//        Snapshot snapshot = insight.capture();
        Snapshot snapshot = createNewSnapshot(insight);
        log.info("Insight[id: {}] is captured.", insight.getId().getValue());
        return snapshot;
    }
}
