package com.project.imdang.member.domain.handler.member;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.MemberDomainService;
import com.project.imdang.member.domain.entity.Member;
import com.project.imdang.member.domain.handler.MemberHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class UpdateInsightCountCommandHandler {

    private final MemberDomainService memberDomainService;
    private final MemberHelper memberHelper;

    @Transactional
    public void increaseInsightCount(MemberId memberId) {
        Member member = memberHelper.get(memberId);
        Member accusedMember = memberDomainService.increaseInsightCount(member);
        memberHelper.save(accusedMember);
    }

    @Transactional
    public void decreaseInsightCount(MemberId memberId) {
        throw new RuntimeException();
    }
}
