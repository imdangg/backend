package com.project.imdang.member.service.domain.valueobject;

import com.project.imdang.member.service.domain.entity.Member;
import org.springframework.stereotype.Component;

import static com.project.imdang.member.service.domain.valueobject.Penalty.EXCHANGE_RESTRICTED_3DAYS;
import static com.project.imdang.member.service.domain.valueobject.Penalty.EXCHANGE_RESTRICTED_5DAYS;
import static com.project.imdang.member.service.domain.valueobject.Penalty.PERMANENT_BANNED;
import static com.project.imdang.member.service.domain.valueobject.Penalty.TEMPORARY_BANNED;

@Component
public class AccusePenaltyPolicy {
// TODO - interface로 변경

    public void apply(Member member) {
        int accusedCount = member.getAccusedCount();
        Penalty penalty = getPenalty(accusedCount);
        if (penalty != null) {
            member.applyPenalty(penalty);
        }
    }

    private Penalty getPenalty(int accusedCount) {
        Penalty penalty = null;
        if (accusedCount == 5) {
            penalty = EXCHANGE_RESTRICTED_3DAYS;
        } else if (accusedCount == 15) {
            penalty = EXCHANGE_RESTRICTED_5DAYS;
        } else if (accusedCount == 30) {
            penalty = TEMPORARY_BANNED;
        } else if (accusedCount == 50) {
            penalty = PERMANENT_BANNED;
        }
        return penalty;
    }
}
