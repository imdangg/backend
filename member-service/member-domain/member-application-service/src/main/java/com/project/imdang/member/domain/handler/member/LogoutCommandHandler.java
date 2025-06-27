package com.project.imdang.member.domain.handler.member;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.MemberDomainService;
import com.project.imdang.member.domain.entity.Member;
import com.project.imdang.member.domain.handler.MemberHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class LogoutCommandHandler {
    private final MemberDomainService memberDomainService;
    private final MemberHelper memberHelper;

    @Transactional
    public Boolean logout(MemberId memberId) {
        Member member = memberHelper.get(memberId);
        Member logout = memberDomainService.logout(member);
        Member saved = memberHelper.save(logout);
        log.info("Member[id: {}] is logout.", saved.getId().getValue());
        return true;
    }
}
