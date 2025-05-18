package com.project.imdang.member.domain.handler.auth;

import com.project.imdang.common.domain.valueobject.OAuthType;
import com.project.imdang.member.domain.MemberDomainService;
import com.project.imdang.member.domain.dto.auth.LoginResult;
import com.project.imdang.member.domain.dto.auth.OAuthLoginCommand;
import com.project.imdang.member.domain.dto.auth.OAuthLoginResponse;
import com.project.imdang.member.domain.dto.auth.TokenResult;
import com.project.imdang.member.domain.entity.Member;
import com.project.imdang.member.domain.handler.MemberHelper;
import com.project.imdang.member.domain.ports.output.client.OAuthApiClientHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class OAuthLoginCommandHandler {
    private final TokenHandler tokenHandler;
    private final Map<OAuthType, OAuthApiClientHandler> apiClients;
    private final MemberDomainService memberDomainService;
    private final MemberHelper memberHelper;

    public OAuthLoginCommandHandler(TokenHandler tokenHandler,
                                    List<OAuthApiClientHandler> apiClients,
                                    MemberDomainService memberDomainService,
                                    MemberHelper memberHelper) {
        this.tokenHandler = tokenHandler;
        this.apiClients = apiClients.stream()
                .collect(Collectors.toUnmodifiableMap(OAuthApiClientHandler::oAuthType, Function.identity()));
        this.memberDomainService = memberDomainService;
        this.memberHelper = memberHelper;
    }

    @Transactional
    public LoginResult login(OAuthLoginCommand loginCommand) {
        OAuthApiClientHandler client = apiClients.get(loginCommand.oAuthType());
        OAuthLoginResponse oAuthInfo = client.getOAuthInfo(loginCommand);

        // 1. 로그인
        Optional<Member> optional = memberHelper.getByOAuthIdAndOAuthTypeAndIsDeleted(oAuthInfo.getId(), oAuthInfo.getOAuthType(), Boolean.FALSE);
        // 지워지지 않은 사용자라면 가져오고, 아니라면 새로 생성
        Member member;
        boolean isJoined = false;
        if (optional.isEmpty()) {
            member = memberDomainService.createMember(oAuthInfo.getId(), oAuthInfo.getOAuthType());
        } else {
            member = optional.get();
            isJoined = (member.getNickname() != null);
        }

        // 2. 토큰 생성
        TokenResult tokenResult = tokenHandler.generateToken(member.getId());

        // 3. RefreshToken 저장
        memberDomainService.storeRefreshToken(member, tokenResult.getRefreshToken());
        memberHelper.save(member);
        return LoginResult.from(tokenResult, isJoined, member.getId(), oAuthInfo.getRefreshToken());
    }
}
