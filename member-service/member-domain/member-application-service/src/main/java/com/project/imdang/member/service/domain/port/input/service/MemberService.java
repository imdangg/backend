package com.project.imdang.member.service.domain.port.input.service;

import com.project.imdang.member.service.domain.dto.JoinCommand;
import com.project.imdang.member.service.domain.dto.LoginResponse;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginCommand;

public interface MemberService {
    LoginResponse login(OAuthLoginCommand loginCommand);
    LoginResponse join(String accessToken, JoinCommand joinCommand);
}
