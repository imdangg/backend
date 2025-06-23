package com.project.imdang.insight.domain.ports.output.repository;

import com.project.imdang.common.domain.valueobject.*;
import com.project.imdang.insight.domain.entity.Insight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InsightRepository {

    Page<Insight> findAll(PageRequest pageRequest);
    Page<Insight> findAllByDate(LocalDate date, PageRequest pageRequest);
    List<Insight> findAllByIds(List<InsightId> insightIds);

    Page<Insight> findAllByDistrict(District district, PageRequest pageRequest);
    Page<Insight> findAllByApartmentComplex(ApartmentComplex apartmentComplex, PageRequest pageRequest);
    Page<Insight> findAllByAddress(Address address, PageRequest pageRequest);
    Optional<Insight> findById(InsightId insightId);
    Insight save(Insight insight);

    List<ApartmentComplex> findDistinctApartmentComplexByMemberId(MemberId memberId);
}
