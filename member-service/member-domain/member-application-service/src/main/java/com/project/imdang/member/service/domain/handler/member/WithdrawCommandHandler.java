package com.project.imdang.member.service.domain.handler.member;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.MemberDomainService;
import com.project.imdang.member.service.domain.dto.oauth.OAuthWithdrawCommand;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.exception.MemberNotFoundException;
import com.project.imdang.member.service.domain.handler.auth.OAuthApiClientHandler;
import com.project.imdang.member.service.domain.ports.output.MemberRepository;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class WithdrawCommandHandler {
    private final MemberRepository memberRepository;
    private final Map<OAuthType, OAuthApiClientHandler> apiClients;
    private final MemberDomainService memberDomainService;

    public WithdrawCommandHandler(MemberRepository memberRepository, List<OAuthApiClientHandler> apiClients, MemberDomainService memberDomainService) {
        this.memberRepository = memberRepository;
        this.apiClients = apiClients.stream()
                .collect(Collectors.toUnmodifiableMap(OAuthApiClientHandler::oAuthType, Function.identity()));
        this.memberDomainService = memberDomainService;
    }

    public void withdraw(UUID memberId, OAuthWithdrawCommand oAuthWithdrawCommand) {
        // 1. 멤버 찾기
        Member member = check(memberId);
        // 2. 탈퇴 처리
        OAuthApiClientHandler withdrawHandler = apiClients.get(oAuthWithdrawCommand.oAuthType());
        withdrawHandler.withdraw(oAuthWithdrawCommand);
        // 3. 사용자 삭제 및 토큰 만료
        memberDomainService.withdraw(member);
        log.info("Member[id:{}] is withdrew", member.getId().getValue());
    }

    private Member check(UUID _memberId) {
        MemberId memberId = new MemberId(_memberId);
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
        return findMember;
    }
}
