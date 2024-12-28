package com.project.imdang.member.service.domain.handler;

import com.project.imdang.member.service.domain.MemberDomainService;
import com.project.imdang.member.service.domain.dto.LoginResponse;
import com.project.imdang.member.service.domain.dto.TokenResponse;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginCommand;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginResponse;
import com.project.imdang.domain.entity.Member;
import com.project.imdang.member.service.domain.exception.MemberDomainException;
import com.project.imdang.domain.ports.output.MemberRepository;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class OAuthLoginCommandHandler {
    private final MemberRepository memberRepository;
    private final TokenRequestHandler tokenRequestHandler;
    private final Map<OAuthType, OAuthApiClientHandler> apiClients;
    private final MemberDomainService memberDomainService;

    public OAuthLoginCommandHandler(MemberRepository memberRepository, TokenRequestHandler tokenRequestHandler, List<OAuthApiClientHandler> apiClients, MemberDomainService memberDomainService) {
        this.memberRepository = memberRepository;
        this.tokenRequestHandler = tokenRequestHandler;
        this.apiClients = apiClients.stream()
                .collect(Collectors.toUnmodifiableMap(OAuthApiClientHandler::oAuthType, Function.identity()));
        this.memberDomainService = memberDomainService;
    }

    public LoginResponse login(OAuthLoginCommand loginCommand) {
        OAuthApiClientHandler client = apiClients.get(loginCommand.oAuthType());
        String accessToken = client.getAccessToken(loginCommand);
        OAuthLoginResponse oAuthInfo = client.getOAuthInfo(accessToken);

        // 1. 로그인
        // TODO - REVIEW
        boolean isJoined = true;
        Optional<Member> optional = memberRepository.findMemberByOAuthIdAndOAuthType(oAuthInfo.getId(), oAuthInfo.getOAuthType());
        Member member;
        if (optional.isEmpty()) {
            member = memberDomainService.createMember(oAuthInfo.getId(), oAuthInfo.getOAuthType());
            saveMember(member);
            isJoined = false;
        } else {
            member = optional.get();
        }

        // 2. 토큰 생성
        TokenResponse tokenResponse = tokenRequestHandler.generate(member);
        // TODO: 3. RefreshToken 저장
        tokenRequestHandler.storeRefreshToken(member.getOAuthId(), tokenResponse.getRefreshToken());
        return LoginResponse.from(tokenResponse, isJoined);
    }

    private Member saveMember(Member member) {
        Member savedMember =  memberRepository.save(member);
        if (savedMember == null) {
            String errorMessage = "Could not save Member!";
            log.error("Could not save Member!");
            throw new MemberDomainException(errorMessage);
        }
        log.info("Member[id : {}] is created.", member.getId().getValue());
        return savedMember;
    }
}
