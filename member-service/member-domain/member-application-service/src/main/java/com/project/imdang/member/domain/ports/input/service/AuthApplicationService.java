package com.project.imdang.member.domain.ports.input.service;

import com.project.imdang.member.domain.dto.auth.JoinMemberCommand;
import com.project.imdang.member.domain.dto.auth.LoginResult;
import com.project.imdang.member.domain.dto.auth.ReissueTokenCommand;
import com.project.imdang.member.domain.dto.auth.TokenResult;
import com.project.imdang.member.domain.dto.auth.OAuthLoginCommand;

public interface AuthApplicationService {
    LoginResult login(OAuthLoginCommand loginCommand);
    void join(JoinMemberCommand joinMemberCommand);
    TokenResult reissue(ReissueTokenCommand reissueTokenCommand);
}
