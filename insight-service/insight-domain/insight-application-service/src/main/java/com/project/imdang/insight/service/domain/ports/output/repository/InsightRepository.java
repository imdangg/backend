package com.project.imdang.insight.service.domain.ports.output.repository;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.Insight;
import com.project.imdang.insight.service.domain.valueobject.ApartmentComplex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface InsightRepository {

    Page<Insight> findAll(PageRequest pageRequest);

    // TODO - CHECK
//    Page<Insight> findAllByIds(List<InsightId> insightIds, PageRequest pageRequest);
    List<Insight> findAllByIds(List<InsightId> insightIds);

    Page<Insight> findAllByApartmentComplex(ApartmentComplex apartmentComplex, PageRequest pageRequest);
    Optional<Insight> findById(InsightId insightId);
    Insight save(Insight insight);
    void deleteById(InsightId insightId);

    List<ApartmentComplex> findDistinctApartmentComplexByMemberId(MemberId memberId);
}
