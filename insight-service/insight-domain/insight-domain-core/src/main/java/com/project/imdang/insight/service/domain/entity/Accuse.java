package com.project.imdang.insight.service.domain.entity;


import com.project.imdang.domain.entity.BaseEntity;
import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.valueobject.AccuseId;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class Accuse extends BaseEntity<AccuseId> {
    private final MemberId accuseMemberId;      // accusedBy
    private final InsightId accusedInsightId;
    private final MemberId accusedMemberId;
    private final ZonedDateTime createdAt;

    @Builder
    public Accuse(AccuseId id, MemberId accuseMemberId, InsightId accusedInsightId, MemberId accusedMemberId, ZonedDateTime createdAt) {
        setId(id);
        this.accuseMemberId = accuseMemberId;
        this.accusedInsightId = accusedInsightId;
        this.accusedMemberId = accusedMemberId;
        this.createdAt = createdAt;
    }

    static Accuse createNewAccuse(MemberId accuseMemberId, InsightId accusedInsightId, MemberId accusedMemberId) {
        return Accuse.builder()
                .accuseMemberId(accuseMemberId)
                .accusedInsightId(accusedInsightId)
                .accusedMemberId(accusedMemberId)
                .createdAt(ZonedDateTime.now())
                .build();
    }
}
