package com.project.imdang.insight.service.domain.entity;


import com.project.imdang.domain.entity.BaseEntity;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.valueobject.AccuseId;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class Accuse extends BaseEntity<AccuseId> {
    private final MemberId accuseMemberId;
    private final MemberId accusedMemberId;
    private final ZonedDateTime accusedAt;

    public Accuse(MemberId accuseMemberId, MemberId accusedMemberId, ZonedDateTime accusedAt) {
        this.accuseMemberId = accuseMemberId;
        this.accusedMemberId = accusedMemberId;
        this.accusedAt = accusedAt;
    }
}
