package com.project.imdang.member.domain;

import com.project.imdang.common.domain.valueobject.Penalty;
import com.project.imdang.member.domain.entity.Member;
import com.project.imdang.member.domain.valueobject.AccusePenaltyPolicy;
import org.springframework.stereotype.Component;

import static com.project.imdang.common.domain.valueobject.Penalty.PERMANENT_BANNED;
import static com.project.imdang.common.domain.valueobject.Penalty.TEMPORARY_BANNED;

@Component
public class AccusePenaltyPolicyImpl implements AccusePenaltyPolicy {

    @Override
    public void apply(Member member) {
        Long accusedCount = member.getAccusedCount();
        Penalty penalty = getPenalty(accusedCount);
        if (penalty != null) {
            member.applyPenalty(penalty);
        }
    }

    private Penalty getPenalty(Long accusedCount) {
        Penalty penalty = null;
        if (accusedCount == 30L) {
            penalty = TEMPORARY_BANNED;
        } else if (accusedCount == 50L) {
            penalty = PERMANENT_BANNED;
        }
        return penalty;
    }
}
