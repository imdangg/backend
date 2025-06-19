package com.project.imdang.member.domain.handler.auth;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.common.domain.valueobject.OAuthType;
import com.project.imdang.member.domain.MemberDomainService;
import com.project.imdang.member.domain.dto.member.OAuthWithdrawCommand;
import com.project.imdang.member.domain.entity.Member;
import com.project.imdang.member.domain.handler.MemberHelper;
import com.project.imdang.member.domain.ports.output.client.OAuthApiClientHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class WithdrawCommandHandler {
    private final Map<OAuthType, OAuthApiClientHandler> apiClients;
    private final MemberDomainService memberDomainService;
    private final MemberHelper memberHelper;

    public WithdrawCommandHandler(List<OAuthApiClientHandler> apiClients,
                                  MemberDomainService memberDomainService,
                                  MemberHelper memberHelper) {
        this.apiClients = apiClients.stream()
                .collect(Collectors.toUnmodifiableMap(OAuthApiClientHandler::oAuthType, Function.identity()));
        this.memberDomainService = memberDomainService;
        this.memberHelper = memberHelper;
    }

    @Transactional
    public void withdraw(OAuthWithdrawCommand oAuthWithdrawCommand) {
        // 1. 멤버 찾기
        MemberId memberId = oAuthWithdrawCommand.getMemberId();
        Member member = memberHelper.getByIdAndIsDeleted(memberId);
        // 2. 탈퇴 처리
        OAuthApiClientHandler withdrawHandler = apiClients.get(oAuthWithdrawCommand.getOAuthType());
        withdrawHandler.withdraw(oAuthWithdrawCommand);
        // 3. 사용자 삭제 및 토큰 만료
        Member withdrew = memberDomainService.withdraw(member);
        Member saved = memberHelper.save(withdrew);
        log.info("Member[id: {}] is withdrew.", saved.getId().getValue());
    }
}
