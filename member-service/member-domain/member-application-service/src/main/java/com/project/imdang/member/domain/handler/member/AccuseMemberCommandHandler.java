package com.project.imdang.member.domain.handler.member;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.MemberDomainService;
import com.project.imdang.member.domain.entity.Member;
import com.project.imdang.member.domain.handler.MemberHelper;
import com.project.imdang.member.domain.valueobject.AccusePenaltyPolicy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class AccuseMemberCommandHandler {

    private final AccusePenaltyPolicy accusePenaltyPolicy;
    private final MemberDomainService memberDomainService;
    private final MemberHelper memberHelper;

    @Transactional
    public void accuse(MemberId memberId) {
        Member member = memberHelper.get(memberId);
        Member accusedMember = memberDomainService.accuseMember(member, accusePenaltyPolicy);
        memberHelper.save(accusedMember);
    }
}
