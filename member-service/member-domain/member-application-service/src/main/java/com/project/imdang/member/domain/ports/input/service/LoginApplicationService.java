package com.project.imdang.member.domain.ports.input.service;

import com.project.imdang.member.domain.dto.login.LoginCommand;
import com.project.imdang.member.domain.dto.login.LoginResult;
import com.project.imdang.member.domain.dto.login.ReissueTokenCommand;
import com.project.imdang.member.domain.dto.login.TokenResult;

public interface LoginApplicationService {
    LoginResult login(LoginCommand loginCommand);
    TokenResult reissue(ReissueTokenCommand reissueTokenCommand);
}
