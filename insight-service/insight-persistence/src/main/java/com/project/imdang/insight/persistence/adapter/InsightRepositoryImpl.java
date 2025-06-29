package com.project.imdang.insight.persistence.adapter;

import com.project.imdang.common.domain.valueobject.BaseId;
import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.entity.Insight;
import com.project.imdang.insight.domain.ports.output.repository.InsightRepository;
import com.project.imdang.common.domain.valueobject.ApartmentComplex;
import com.project.imdang.common.domain.valueobject.District;
import com.project.imdang.insight.persistence.repository.InsightJpaRepository;
import com.project.imdang.insight.persistence.repository.InsightSpecification;
import com.project.imdang.insight.persistence.entity.InsightEntity;
import com.project.imdang.insight.persistence.mapper.InsightPersistenceMapper;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class InsightRepositoryImpl implements InsightRepository {

    private final InsightJpaRepository insightJpaRepository;
    private final InsightPersistenceMapper insightPersistenceMapper;

    /**
     * 페이징으로 인사이트 목록 조회
     */
    @Override
    public Page<Insight> findAll(PageRequest pageRequest) {
        return insightJpaRepository.findAll(pageRequest)
                .map(insightPersistenceMapper::insightEntityToInsight);
    }

    /**
     * 날짜 + 페이징으로 인사이트 목록 조회
     */
    @Override
    public Page<Insight> findAllByDate(LocalDate date, PageRequest pageRequest) {
        final ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime startOfDay = date.atStartOfDay(zoneId);
        ZonedDateTime endOfDay = date.atTime(LocalTime.MAX).atZone(zoneId);
        return insightJpaRepository.findAllByCreatedAtBetween(startOfDay, endOfDay, pageRequest)
                .map(insightPersistenceMapper::insightEntityToInsight);
    }

    /**
     * 지역구 + 페이징으로 인사이트 목록 조회
     */
    @Override
    public Page<Insight> findAllByDistrict(District district, PageRequest pageRequest) {
        Specification<InsightEntity> specification =
                Specification.where(InsightSpecification.equalsSiDo(district.getSiDo()))
                        .and(InsightSpecification.equalsSiGunGu(district.getSiGunGu()))
                        .and(InsightSpecification.equalsEupMyeonDong(district.getEupMyeonDong()));
        return insightJpaRepository.findAll(specification, pageRequest)
                .map(insightPersistenceMapper::insightEntityToInsight);
    }

    /**
     * 단지 + 페이징으로 인사이트 목록 조회
     */
    @Override
    public Page<Insight> findAllByApartmentComplex(ApartmentComplex apartmentComplex, PageRequest pageRequest) {
        Specification<InsightEntity> specification = Specification.where(InsightSpecification.equalsApartmentComplexName(apartmentComplex.getName()));
        return insightJpaRepository.findAll(specification, pageRequest)
                .map(insightPersistenceMapper::insightEntityToInsight);
    }

    /**
     * 사용자 + 페이징으로 인사이트 목록 조회
     */
    @Override
    public Page<Insight> findAllByMemberId(MemberId memberId, PageRequest pageRequest) {
        Specification<InsightEntity> specification = Specification.where(InsightSpecification.equalsMemberId(memberId.getValue().toString()));
        return insightJpaRepository.findAll(specification, pageRequest)
                .map(insightPersistenceMapper::insightEntityToInsight);
    }

    /**
     * ID로 인사이트 조회
     */
    @Override
    public Optional<Insight> findById(InsightId insightId) {
        UUID _insightId = insightId.getValue();
        return insightJpaRepository.findById(_insightId)
                .map(insightPersistenceMapper::insightEntityToInsight);
    }

    /**
     * 인사이트 저장
     */
    @Override
    public Insight save(Insight insight) {
        InsightEntity insightEntity = insightPersistenceMapper.insightToInsightEntity(insight);
        InsightEntity saved = insightJpaRepository.save(insightEntity);
        return insightPersistenceMapper.insightEntityToInsight(saved);
    }

    /**
     * 사용자가 다녀온 단지 목록 조회
     */
    @Override
    public List<ApartmentComplex> findDistinctApartmentComplexByMemberId(MemberId memberId) {
        return insightJpaRepository.findDistinctApartmentComplexByMemberId(memberId.getValue());
    }

    /**
     * 사용자의 보관 중인 인사이트 지역 목록 조회
     */
    @Override
    public List<Object[]> findAllDistrictByMemberId(MemberId memberId) {
        return insightJpaRepository.findAllDistrictByMemberId(memberId.getValue().toString());
    }

    /**
     * 사용자 + 자치구로 자치구 별 단지 수 및 인사이트 개수 조회
     */
    @Override
    public Long[] countAllByMemberIdAndDistrict(MemberId memberId, District district) {
        Tuple tuple = insightJpaRepository.countAllByMemberIdAndDistrict(memberId.getValue().toString(), district.getSiDo(), district.getSiGunGu(), district.getEupMyeonDong());
        Long apartmentComplexCount = tuple.get("apartment_complex_count", Long.class);
        Long insightCount = tuple.get("insight_count", Long.class);
        return new Long[]{apartmentComplexCount, insightCount};
    }

    /**
     * 사용자 + 자치구로 단지별 인사이트 개수 조회
     */
    @Override
    public List<Object[]> findAllDistinctApartmentComplexAndInsightCountByMemberIdAndDistrict(MemberId memberId, District district) {
        return insightJpaRepository.findAllApartmentComplexAndInsightCountByMemberIdAndDistrict(memberId.getValue().toString(), district.getSiDo(), district.getSiGunGu(), district.getEupMyeonDong());
    }

    /**
     * 사용자 + 단지 + 페이징으로 보관중인 인사이트 중 자신이 작성한 목록 조회
     */
    @Override
    public Page<Insight> findAllByMemberIdAndApartmentComplexAndOnlyMine(MemberId memberId, ApartmentComplex apartmentComplex, PageRequest pageRequest) {
        return insightJpaRepository.findAllByMemberIdAndApartmentComplexAndOnlyMine(memberId.getValue().toString(), apartmentComplex.getName(), pageRequest)
                .map(insightPersistenceMapper::insightEntityToInsight);
    }

    /**
     * 사용자 + 단지 + 페이징으로 보관중인 인사이트 목록 조회
     */
    @Override
    public Page<Insight> findAllByMemberIdAndApartmentComplex(MemberId memberId, ApartmentComplex apartmentComplex, PageRequest pageRequest) {
        return insightJpaRepository.findAllByMemberIdAndApartmentComplex(memberId.getValue().toString(), apartmentComplex.getName(), pageRequest)
                .map(insightPersistenceMapper::insightEntityToInsight);
    }

    /**
     * 사용자 + 지역구 + 페이징으로 보관중인 인사이트 중 자신이 작성한 목록 조회
     */
    @Override
    public Page<Insight> findAllByMemberIdAndDistrictAndOnlyMine(MemberId memberId, District district, PageRequest pageRequest) {
       return insightJpaRepository.findAllByMemberIdAndDistrictAndOnlyMine(memberId.getValue().toString()
                , district.getSiDo()
                , district.getSiGunGu()
                , district.getEupMyeonDong(),
                pageRequest).map(insightPersistenceMapper::insightEntityToInsight);
    }

    /**
     * 사용자 + 지역구 + 페이징으로 보관중인 인사이트 목록 조회
     */
    @Override
    public Page<Insight> findAllByMemberIdAndDistrict(MemberId memberId, District district, PageRequest pageRequest) {
        return insightJpaRepository.findAllByMemberIdAndDistrict(memberId.getValue().toString()
                , district.getSiDo()
                , district.getSiGunGu()
                , district.getEupMyeonDong(),
                pageRequest).map(insightPersistenceMapper::insightEntityToInsight);
    }
}
