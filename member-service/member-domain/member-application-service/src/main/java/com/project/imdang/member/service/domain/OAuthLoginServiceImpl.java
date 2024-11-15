package com.project.imdang.member.service.domain;

import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginParamsCommand;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginResponse;
import com.project.imdang.member.service.domain.handler.OAuthLoginParamsCommandHandler;
import com.project.imdang.member.service.domain.port.input.service.OAuthLoginService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OAuthLoginServiceImpl implements OAuthLoginService {

    private final OAuthLoginParamsCommandHandler oAuthLoginParamsCommandHandler;
    @Override
    public void login(OAuthLoginParamsCommand loginCommand) {
        OAuthLoginResponse loginResponse = oAuthLoginParamsCommandHandler.request(loginCommand);
        //TODO:회원가입 여부 확인
    }
}
