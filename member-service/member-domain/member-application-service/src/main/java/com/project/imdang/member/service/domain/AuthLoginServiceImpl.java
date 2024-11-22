package com.project.imdang.member.service.domain;

import com.project.imdang.member.service.domain.dto.TokenResponse;
import com.project.imdang.member.service.domain.dto.LoginResponse;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginParamsCommand;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginResponse;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.handler.OAuthLoginParamsCommandHandler;
import com.project.imdang.member.service.domain.handler.TokenRequestHandler;
import com.project.imdang.member.service.domain.port.input.service.AuthLoginService;
import com.project.imdang.member.service.domain.port.output.repository.MemberRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class AuthLoginServiceImpl implements AuthLoginService {

    private final OAuthLoginParamsCommandHandler oAuthLoginParamsCommandHandler;
    private final TokenRequestHandler tokenRequestHandler;
    private final MemberRespository memberRespository;
    @Override
    public LoginResponse login(OAuthLoginParamsCommand loginCommand) {
        OAuthLoginResponse loginResponse = oAuthLoginParamsCommandHandler.request(loginCommand);
        boolean isJoined = memberRespository.existByOauthIdAndType(loginResponse);
        log.info("가입 여부 확인 : {}", isJoined);

        // 1. 로그인
        Member member = memberRespository.findOrCreateMember(loginResponse);
        // 2. 토큰 생성
        TokenResponse tokenResponse = tokenRequestHandler.generate(member);
        // TODO: 3. RefreshToken 저장
        tokenRequestHandler.storeRefreshToken(member.getOAuthId(), tokenResponse.getRefreshToken());

        return LoginResponse.from(tokenResponse, isJoined);
    }
}
