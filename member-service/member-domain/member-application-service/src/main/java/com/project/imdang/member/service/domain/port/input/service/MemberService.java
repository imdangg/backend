package com.project.imdang.member.service.domain.port.input.service;

import com.project.imdang.member.service.domain.dto.LoginResponse;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginParamsCommand;

public interface MemberService {
    LoginResponse login(OAuthLoginParamsCommand loginCommand);
}
