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

@Slf4j
@RequiredArgsConstructor
@Component
public class UpdateMemberCommandHandler {

    private final MemberDomainService memberDomainService;
    private final MemberHelper memberHelper;

    @Transactional
    public Boolean update(MemberId memberId) {
        Member member = memberHelper.get(memberId);
        Member updatedMember = memberDomainService.updateMember(member);
        memberHelper.save(updatedMember);
        return true;
    }
}
