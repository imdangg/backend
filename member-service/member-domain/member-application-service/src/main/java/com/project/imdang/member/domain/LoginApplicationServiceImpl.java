package com.project.imdang.member.domain;

import com.project.imdang.member.domain.dto.login.LoginCommand;
import com.project.imdang.member.domain.dto.login.LoginResult;
import com.project.imdang.member.domain.dto.login.ReissueTokenCommand;
import com.project.imdang.member.domain.dto.login.TokenResult;
import com.project.imdang.member.domain.handler.auth.LoginCommandHandler;
import com.project.imdang.member.domain.handler.auth.ReissueTokenCommandHandler;
import com.project.imdang.member.domain.ports.input.service.LoginApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginApplicationServiceImpl implements LoginApplicationService {

    private final LoginCommandHandler loginCommandHandler;
    private final ReissueTokenCommandHandler reissueTokenCommandHandler;

    @Override
    public LoginResult login(LoginCommand loginCommand) {
        return loginCommandHandler.login(loginCommand);
    }

    @Override
    public TokenResult reissue(ReissueTokenCommand reissueTokenCommand) {
        return reissueTokenCommandHandler.reissue(reissueTokenCommand);
    }
}
