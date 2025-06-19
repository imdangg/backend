package com.project.imdang.member.domain;

import com.project.imdang.member.domain.dto.auth.JoinMemberCommand;
import com.project.imdang.member.domain.dto.auth.LoginResult;
import com.project.imdang.member.domain.dto.auth.ReissueTokenCommand;
import com.project.imdang.member.domain.dto.auth.TokenResult;
import com.project.imdang.member.domain.dto.auth.OAuthLoginCommand;
import com.project.imdang.member.domain.handler.auth.JoinCommandHandler;
import com.project.imdang.member.domain.handler.auth.OAuthLoginCommandHandler;
import com.project.imdang.member.domain.handler.auth.ReissueTokenCommandHandler;
import com.project.imdang.member.domain.ports.input.service.AuthApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthApplicationServiceImpl implements AuthApplicationService {

    private final OAuthLoginCommandHandler oAuthLoginCommandHandler;
    private final JoinCommandHandler joinCommandHandler;
    private final ReissueTokenCommandHandler reissueTokenCommandHandler;

    @Override
    public LoginResult login(OAuthLoginCommand loginCommand) {
        return oAuthLoginCommandHandler.login(loginCommand);
    }

    @Override
    public void join(JoinMemberCommand joinMemberCommand) {
        joinCommandHandler.join(joinMemberCommand);
    }

    @Override
    public TokenResult reissue(ReissueTokenCommand reissueTokenCommand) {
        return reissueTokenCommandHandler.reissue(reissueTokenCommand);
    }
}
