package com.project.imdang.member.service.domain.handler;

import com.project.imdang.member.service.domain.MemberDomainService;
import com.project.imdang.member.service.domain.dto.LoginResponse;
import com.project.imdang.member.service.domain.dto.TokenResponse;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginCommand;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginResponse;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.exception.MemberDomainException;
import com.project.imdang.member.service.domain.ports.output.MemberRespository;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class OAuthLoginCommandHandler {
    private final MemberRespository memberRespository;
    private final TokenRequestHandler tokenRequestHandler;
    private final Map<OAuthType, OAuthApiClientHandler> apiClients;
    private final MemberDomainService memberDomainService;

    public OAuthLoginCommandHandler(MemberRespository memberRespository, TokenRequestHandler tokenRequestHandler, List<OAuthApiClientHandler> apiClients, MemberDomainService memberDomainService) {
        this.memberRespository = memberRespository;
        this.tokenRequestHandler = tokenRequestHandler;
        this.apiClients = apiClients.stream()
                .collect(Collectors.toUnmodifiableMap(OAuthApiClientHandler::oAuthType, Function.identity()));
        this.memberDomainService = memberDomainService;
    }

    public LoginResponse login(OAuthLoginCommand loginCommand) {
        OAuthApiClientHandler client = apiClients.get(loginCommand.oAuthType());
        String accessToken = client.getAccessToken(loginCommand);
        OAuthLoginResponse oAuthInfo = client.getOAuthInfo(accessToken);

        // TODO - REVIEW
        Member member = memberRespository.findMemberByOAuthIdAndOAuthType(oAuthInfo.getId(), oAuthInfo.getOAuthType())
                .orElseGet(() -> {
                    Member created = memberDomainService.createMember(oAuthInfo.getId(), oAuthInfo.getOAuthType());
                    saveMember(created);
                    return created;
                });

        // 1. 로그인
        // 2. 토큰 생성
        TokenResponse tokenResponse = tokenRequestHandler.generate(member);
        // TODO: 3. RefreshToken 저장
        tokenRequestHandler.storeRefreshToken(member.getOAuthId(), tokenResponse.getRefreshToken());
        return LoginResponse.from(tokenResponse);
    }

    private Member saveMember(Member member) {
        Member savedMember =  memberRespository.save(member);
        if (savedMember == null) {
            String errorMessage = "Could not save Member!";
            log.error("Could not save Member!");
            throw new MemberDomainException(errorMessage);
        }
        log.info("Member[id : {}] is created.", member.getMemberId().getValue());
        return savedMember;
    }
}
