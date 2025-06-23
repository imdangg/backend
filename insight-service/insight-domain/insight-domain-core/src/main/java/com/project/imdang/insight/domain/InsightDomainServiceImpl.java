package com.project.imdang.insight.domain;

import com.project.imdang.common.domain.valueobject.Access;
import com.project.imdang.common.domain.valueobject.Address;
import com.project.imdang.common.domain.valueobject.ApartmentComplex;
import com.project.imdang.common.domain.valueobject.ComplexEnvironment;
import com.project.imdang.common.domain.valueobject.Infra;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.common.domain.valueobject.VisitMethod;
import com.project.imdang.common.domain.valueobject.VisitTime;
import com.project.imdang.insight.domain.entity.Accuse;
import com.project.imdang.insight.domain.entity.Insight;
import com.project.imdang.insight.domain.entity.InsightImage;
import com.project.imdang.insight.domain.entity.Recommend;
import com.project.imdang.insight.domain.event.InsightAccusedEvent;
import com.project.imdang.insight.domain.event.InsightDeletedEvent;
import com.project.imdang.insight.domain.event.InsightRecommendedEvent;
import com.project.imdang.insight.domain.event.InsightUpdatedEvent;
import com.project.imdang.insight.domain.exception.InsightDomainException;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class InsightDomainServiceImpl implements InsightDomainService {
// TODO - CHECK : 왜 DomainServiceImpl을 bean으로 등록하는가?

    @Override
    public Insight createInsight(Insight insight, List<String> images) {
        insight.initialize(images);
        log.info("Insight[id: {}] is created.", insight.getId().getValue());
        return insight;
    }

    @Override
    public InsightUpdatedEvent updateInsight(Insight insight,
                                             MemberId memberId,
                                             List<String> images,
                                             String title,
                                             Address address,
                                             ApartmentComplex apartmentComplex,
                                             LocalDate visitAt,
                                             Set<VisitTime> visitTimes,
                                             Set<VisitMethod> visitMethods,
                                             Access access,
                                             String summary,
                                             Infra infra,
                                             ComplexEnvironment complexEnvironment) {
        insight.urlsToInsightImages(insight.getId(), images);
        insight.update(memberId, insight.getImages(), title, address, apartmentComplex,
                visitAt, visitTimes, visitMethods, access, summary,
                infra, complexEnvironment);
        log.info("Insight[id: {}] is updated.", insight.getId().getValue());
        return new InsightUpdatedEvent(insight, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public InsightDeletedEvent deleteInsight(Insight insight, MemberId deletedBy) {

        if (!deletedBy.equals(insight.getMemberId())) {
            throw new InsightDomainException("Author does not match!");
        }
        insight.delete();
        log.info("Insight[id: {}] is deleted.", insight.getId().getValue());
        return new InsightDeletedEvent(insight, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public InsightRecommendedEvent recommendInsight(Insight insight, MemberId recommendedBy) {
        Recommend recommend = insight.recommend(recommendedBy);
        log.info("Insight[id: {}] is recommended.", insight.getId().getValue());
        return new InsightRecommendedEvent(insight, recommend, recommend.getCreatedAt());
    }

    @Override
    public InsightAccusedEvent accuseInsight(Insight insight, MemberId accusedBy) {
        Accuse accuse = insight.accuse(accusedBy);
        log.info("Insight[id: {}] is accused.", insight.getId().getValue());
        return new InsightAccusedEvent(insight, accuse, accuse.getCreatedAt());
    }

    @Override
    public void viewInsight(Insight insight) {
        insight.view();
        log.info("Insight[id: {}] is viewed.", insight.getId().getValue());
    }
}
