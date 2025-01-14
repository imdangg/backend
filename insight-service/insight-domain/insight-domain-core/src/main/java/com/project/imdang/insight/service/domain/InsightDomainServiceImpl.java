package com.project.imdang.insight.service.domain;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.Accuse;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.entity.Snapshot;
import com.project.imdang.insight.service.domain.event.InsightAccusedEvent;
import com.project.imdang.insight.service.domain.event.InsightDeletedEvent;
import com.project.imdang.insight.service.domain.event.InsightUpdatedEvent;
import com.project.imdang.insight.service.domain.exception.InsightDomainException;
import com.project.imdang.insight.service.domain.valueobject.Access;
import com.project.imdang.insight.service.domain.valueobject.Address;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import com.project.imdang.insight.service.domain.valueobject.ComplexEnvironment;
import com.project.imdang.insight.service.domain.valueobject.ComplexFacility;
import com.project.imdang.insight.service.domain.valueobject.FavorableNews;
import com.project.imdang.insight.service.domain.valueobject.Infra;
import com.project.imdang.insight.service.domain.valueobject.VisitMethod;
import com.project.imdang.insight.service.domain.valueobject.VisitTime;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

@Slf4j
public class InsightDomainServiceImpl implements InsightDomainService {
// TODO - CHECK : 왜 DomainServiceImpl을 bean으로 등록하는가?
/*
    @Override
    public Insight validateAndEvaluateInsight(Insight insight) {
        insight.validateAndEvaluate();
        log.info("Insight[id: {}] is validated.", insight.getId().getValue());
        return insight;
    }*/

    @Override
    public Insight createInsight(Insight insight, String mainImage) {
        insight.initialize(mainImage);
        log.info("Insight[id: {}] is created.", insight.getId().getValue());
        return insight;
    }

    @Override
    public InsightUpdatedEvent updateInsight(Insight insight,
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
                                             int score) {
        insight.update(memberId, mainImage, title, address, apartmentComplex,
                visitAt, visitTimes, visitMethods, access, summary,
                infra, complexEnvironment, complexFacility, favorableNews, score);
        log.info("Insight[id: {}] is updated.", insight.getId().getValue());
        return new InsightUpdatedEvent(insight, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public InsightDeletedEvent deleteInsight(Insight insight, MemberId deletedBy) {

        if (!deletedBy.equals(insight.getMemberId())) {
            throw new InsightDomainException("Author does not match!");
        }
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
        Accuse accuse = insight.accuse(accusedBy);
        log.info("Insight[id: {}] is accused.", insight.getId().getValue());
        // TODO - CHECK : accuse.getCreatedAt()
        return new InsightAccusedEvent(insight, accuse, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public Snapshot captureInsight(Insight insight) {
        Snapshot snapshot = insight.capture();
        log.info("Insight[id: {}] is captured.", insight.getId().getValue());
        return snapshot;
    }
}
