package com.project.imdang.member.domain.handler.member;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.MemberDomainService;
import com.project.imdang.member.domain.ports.output.client.OAuthClientHandler;
import com.project.imdang.member.domain.dto.member.WithdrawCommand;
import com.project.imdang.member.domain.entity.Member;
import com.project.imdang.member.domain.handler.MemberHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class WithdrawCommandHandler {
    private final MemberDomainService memberDomainService;
    private final MemberHelper memberHelper;
    private final OAuthClientHandler oAuthClientHandler;

    @Transactional
    public Boolean withdraw(WithdrawCommand withdrawCommand) {
        // 1. 멤버 찾기
        final MemberId memberId = withdrawCommand.memberId();
        Member member = memberHelper.getByIdAndIsDeleted(memberId);
        // 2. 탈퇴 처리
        oAuthClientHandler.withdraw(withdrawCommand.provider(), withdrawCommand.identifier());
        // 3. 사용자 삭제 및 토큰 만료
        Member withdrew = memberDomainService.withdraw(member);
        Member saved = memberHelper.save(withdrew);
        log.info("Member[id: {}] is withdrew.", saved.getId().getValue());
        return true;
    }
}
