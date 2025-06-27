package com.project.imdang.member.domain.handler.auth;

import com.project.imdang.common.domain.valueobject.OAuthProvider;
import com.project.imdang.member.domain.MemberDomainService;
import com.project.imdang.member.domain.dto.login.LoginCommand;
import com.project.imdang.member.domain.dto.login.LoginResult;
import com.project.imdang.member.domain.dto.login.TokenResult;
import com.project.imdang.member.domain.entity.Member;
import com.project.imdang.member.domain.handler.MemberHelper;
import com.project.imdang.member.domain.ports.output.client.OAuthClientHandler;
import com.project.imdang.member.domain.ports.output.client.OAuthInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoginCommandHandler {

    private final TokenHandler tokenHandler;
    private final MemberDomainService memberDomainService;
    private final MemberHelper memberHelper;
    private final OAuthClientHandler oAuthClientHandler;

    @Transactional
    public LoginResult login(LoginCommand loginCommand) {

        final OAuthProvider provider = loginCommand.provider();
        OAuthInfo oAuthInfo = oAuthClientHandler.getOAuthInfo(provider, loginCommand.identifier());

        // 1. 로그인
        Optional<Member> optional = memberHelper.getByOAuthIdAndOAuthProviderAndIsDeleted(oAuthInfo.getId(), provider, Boolean.FALSE);
        // 지워지지 않은 사용자라면 가져오고, 아니라면 새로 생성
        Member member;
        boolean isJoined = false;
        if (optional.isEmpty()) {
            member = memberDomainService.createMember(oAuthInfo.getId(), provider);
        } else {
            member = optional.get();
            isJoined = (member.getNickname() != null);
        }

        // 2. 토큰 생성
        TokenResult tokenResult = tokenHandler.generateToken(member.getId());

        // 3. RefreshToken 저장
        memberDomainService.storeRefreshToken(member, tokenResult.getRefreshToken());
        memberHelper.save(member);
        return LoginResult.builder()
                .memberId(member.getId().getValue())
                .isJoined(isJoined)
                .accessToken(tokenResult.getAccessToken())
                .refreshToken(tokenResult.getRefreshToken())
                .build();
    }
}
