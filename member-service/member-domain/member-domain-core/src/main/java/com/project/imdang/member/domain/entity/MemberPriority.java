package com.project.imdang.member.domain.entity;

import com.project.imdang.common.domain.entity.AggregateRoot;
import com.project.imdang.common.domain.valueobject.*;
import com.project.imdang.member.domain.valueobject.AccusePenaltyPolicy;
import com.project.imdang.member.domain.valueobject.MemberPriorityId;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class MemberPriority extends AggregateRoot<MemberPriorityId> {

    private final MemberId memberId;
    private final String first;
    private final String second;
    private final String third;

    @Builder
    public MemberPriority(MemberPriorityId memberPriorityId, MemberId memberId, String first, String second, String third) {
        setId(memberPriorityId);
        this.memberId = memberId;
        this.first = first;
        this.second = second;
        this.third = third;
    }
}
