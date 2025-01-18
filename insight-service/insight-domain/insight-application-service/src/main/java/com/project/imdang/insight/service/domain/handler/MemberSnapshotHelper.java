package com.project.imdang.insight.service.domain.handler;

import com.project.imdang.domain.valueobject.InsightId;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.insight.service.domain.entity.MemberSnapshot;
import com.project.imdang.insight.service.domain.exception.InsightDomainException;
import com.project.imdang.insight.service.domain.ports.output.repository.MemberSnapshotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberSnapshotHelper {

    private final MemberSnapshotRepository memberSnapshotRepository;

    public void save(MemberSnapshot memberSnapshot) {
        MemberSnapshot saved = memberSnapshotRepository.save(memberSnapshot);
        if (saved == null) {
            String errorMessage = "Could not save memberSnapshot!";
            log.error(errorMessage);
            throw new InsightDomainException(errorMessage);
        }
        log.info("memberSnapshot[id: {}] is saved.", saved.getId().getValue());
    }

    public void deleteByMemberIdAndInsightId(MemberId memberId, InsightId insightId) {
        memberSnapshotRepository.deleteByMemberIdAndInsightId(memberId, insightId);
    }
}
