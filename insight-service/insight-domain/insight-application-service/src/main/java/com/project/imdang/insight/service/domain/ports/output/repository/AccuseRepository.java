package com.project.imdang.insight.service.domain.ports.output.repository;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.Accuse;

import java.util.Optional;

public interface AccuseRepository {
    Optional<Accuse> findByAccuseMemberIdAndAccusedInsightId(MemberId accuseMemberId, InsightId accusedInsightId);
    Accuse save(Accuse accuse);
}
