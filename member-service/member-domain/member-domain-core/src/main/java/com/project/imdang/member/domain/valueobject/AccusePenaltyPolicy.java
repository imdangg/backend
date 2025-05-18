package com.project.imdang.member.domain.valueobject;

import com.project.imdang.member.domain.entity.Member;

public interface AccusePenaltyPolicy {
    void apply(Member member);
}
