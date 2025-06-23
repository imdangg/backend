package com.project.imdang.insight.domain.ports.output.repository;

import com.project.imdang.common.domain.valueobject.ApartmentComplex;
import com.project.imdang.common.domain.valueobject.District;
import com.project.imdang.common.domain.valueobject.InsightId;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.insight.domain.entity.Insight;
import com.project.imdang.insight.domain.entity.InsightImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface InsightImageRepository {

//    Page<InsightImage> findAll(PageRequest pageRequest);
//    Page<InsightImage> findAllByDate(LocalDate date, PageRequest pageRequest);
    List<InsightImage> findByInsightId(InsightId insightId);
    List<InsightImage> findByInsightIdIn(List<InsightId> insightIds);
//
//    Page<InsightImage> findAllByDistrict(District district, PageRequest pageRequest);
//    Page<InsightImage> findAllByApartmentComplex(ApartmentComplex apartmentComplex, PageRequest pageRequest);
//    Optional<InsightImage> findById(InsightId insightId);

    InsightImage save(InsightImage image);
    List<InsightImage> saveAll(List<InsightImage> images);
    void deleteByInsightId(UUID insightId);  // 구현체에서 작성할 메서드

}
