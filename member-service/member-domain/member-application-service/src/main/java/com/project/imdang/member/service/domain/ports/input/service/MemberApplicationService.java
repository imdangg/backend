package com.project.imdang.member.service.domain.ports.input.service;

import com.project.imdang.member.service.domain.dto.JoinCommand;
import com.project.imdang.member.service.domain.dto.LoginResponse;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginCommand;

public interface MemberApplicationService {
    LoginResponse login(OAuthLoginCommand loginCommand);
    void join(String accessToken, JoinCommand joinCommand);
}
