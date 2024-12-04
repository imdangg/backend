package com.project.imdang.member.service.domain;

import com.project.imdang.member.service.domain.dto.JoinCommand;
import com.project.imdang.member.service.domain.dto.LoginResponse;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginCommand;
import com.project.imdang.member.service.domain.handler.JoinCommandHandler;
import com.project.imdang.member.service.domain.handler.OAuthLoginCommandHandler;
import com.project.imdang.member.service.domain.ports.input.service.MemberApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MemberApplicationServiceImpl implements MemberApplicationService {

    private final OAuthLoginCommandHandler oAuthLoginCommandHandler;
    private final JoinCommandHandler joinCommandHandler;
    @Override
    public LoginResponse login(OAuthLoginCommand loginCommand) {
        return oAuthLoginCommandHandler.login(loginCommand);
    }

    @Override
    public LoginResponse join(String accessToken, JoinCommand joinCommand) {
       return joinCommandHandler.join(accessToken, joinCommand);
    }
}
