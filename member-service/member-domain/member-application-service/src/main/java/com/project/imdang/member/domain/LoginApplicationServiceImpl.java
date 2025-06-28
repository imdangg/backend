package com.project.imdang.member.domain;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.dto.login.ReissueTokenCommand;
import com.project.imdang.member.domain.dto.login.TokenResult;
import com.project.imdang.member.domain.handler.auth.ReissueTokenCommandHandler;
import com.project.imdang.member.domain.handler.member.LogoutCommandHandler;
import com.project.imdang.member.domain.ports.input.service.LoginApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginApplicationServiceImpl implements LoginApplicationService {

    private final ReissueTokenCommandHandler reissueTokenCommandHandler;
    private final LogoutCommandHandler logoutCommandHandler;

    @Override
    public TokenResult reissue(ReissueTokenCommand reissueTokenCommand) {
        return reissueTokenCommandHandler.reissue(reissueTokenCommand);
    }

    @Override
    public Boolean logout(MemberId memberId) {
        return logoutCommandHandler.logout(memberId);
    }
}
