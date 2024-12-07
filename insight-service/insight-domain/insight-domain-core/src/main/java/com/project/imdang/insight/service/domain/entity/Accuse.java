package com.project.imdang.insight.service.domain.entity;


import com.project.imdang.domain.entity.BaseEntity;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.valueobject.AccuseId;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
public class Accuse extends BaseEntity<AccuseId> {
    private final MemberId accuseMemberId;
    private final MemberId accusedMemberId;
    private final ZonedDateTime createdAt;

    @Builder
    public Accuse(AccuseId id, MemberId accuseMemberId, MemberId accusedMemberId, ZonedDateTime createdAt) {
        setId(id);
        this.accuseMemberId = accuseMemberId;
        this.accusedMemberId = accusedMemberId;
        this.createdAt = createdAt;
    }

    static Accuse createNewAccuse(MemberId accuseMemberId, MemberId accusedMemberId) {
        return Accuse.builder()
                .accuseMemberId(accuseMemberId)
                .accusedMemberId(accusedMemberId)
                .createdAt(ZonedDateTime.now())
                .build();
    }
}
