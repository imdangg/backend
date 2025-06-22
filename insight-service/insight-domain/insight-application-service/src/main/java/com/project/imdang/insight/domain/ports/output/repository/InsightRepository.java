package com.project.imdang.insight.domain.ports.output.repository;

import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.entity.Insight;
import com.project.imdang.common.domain.valueobject.ApartmentComplex;
import com.project.imdang.common.domain.valueobject.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InsightRepository {

    Page<Insight> findAll(PageRequest pageRequest);
    Page<Insight> findAllByDate(LocalDate date, PageRequest pageRequest);

    Page<Insight> findAllByDistrict(District district, PageRequest pageRequest);
    Page<Insight> findAllByApartmentComplex(ApartmentComplex apartmentComplex, PageRequest pageRequest);
    Page<Insight> findAllByMemberId(MemberId memberId, PageRequest pageRequest);
    Optional<Insight> findById(InsightId insightId);
    Insight save(Insight insight);

    List<ApartmentComplex> findDistinctApartmentComplexByMemberId(MemberId memberId);
    List<Object[]> findAllDistrictByMemberId(MemberId memberId);
    Long[] countAllByMemberIdAndDistrict(MemberId memberId, District district);
    List<Object[]> findAllDistinctApartmentComplexAndInsightCountByMemberIdAndDistrict(MemberId memberId, District district);
    Page<Insight> findAllByMemberIdAndApartmentComplexAndOnlyMine(MemberId memberId, ApartmentComplex apartmentComplex, PageRequest pageRequest);
    Page<Insight> findAllByMemberIdAndApartmentComplex(MemberId memberId, ApartmentComplex apartmentComplex, PageRequest pageRequest);
    Page<Insight> findAllByMemberIdAndDistrictAndOnlyMine(MemberId memberId, District district, PageRequest pageRequest);
    Page<Insight> findAllByMemberIdAndDistrict(MemberId memberId, District district, PageRequest pageRequest);

}
