package com.project.imdang.member.service.domain.handler.member;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.MemberDomainService;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.handler.MemberHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class LogoutCommandHandler {
    private final MemberDomainService memberDomainService;
    private final MemberHelper memberHelper;

    @Transactional
    public void logout(UUID _memberId) {

        MemberId memberId = new MemberId(_memberId);
        Member member = memberHelper.get(memberId);

        Member logout = memberDomainService.logout(member);
        Member saved = memberHelper.save(logout);
        log.info("Member[id:{}] is logout.", saved.getId().getValue());
    }
}
