package com.project.imdang.insight.service.domain.ports.output.repository;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.insight.service.domain.entity.Insight;

import java.util.Optional;

public interface InsightRepository {
    Optional<Insight> findById(InsightId insightId);
    Insight save(Insight insight);
    void deleteById(InsightId insightId);
}
