package com.project.imdang.insight.service.domain.ports.output.repository;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.insight.service.domain.entity.Insight;

import java.util.Optional;
import java.util.UUID;

public interface InsightRepository {
    Optional<Insight> findInsight(InsightId insightId);
    Insight save(Insight insight);
    void deleteInsight(InsightId insightId);
}
