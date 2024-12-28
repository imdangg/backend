package com.project.imdang.member.service.domain;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.dto.JoinCommand;
import com.project.imdang.member.service.domain.dto.LoginResponse;
import com.project.imdang.member.service.domain.dto.TokenResponse;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginCommand;
import com.project.imdang.member.service.domain.handler.JoinCommandHandler;
import com.project.imdang.member.service.domain.handler.OAuthLoginCommandHandler;
import com.project.imdang.member.service.domain.handler.TokenRequestHandler;
import com.project.imdang.member.service.domain.ports.input.service.MemberApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberApplicationServiceImpl implements MemberApplicationService {

    private final OAuthLoginCommandHandler oAuthLoginCommandHandler;
    private final JoinCommandHandler joinCommandHandler;
    private final TokenRequestHandler tokenRequestHandler;

    @Override
    public LoginResponse login(OAuthLoginCommand loginCommand) {
        return oAuthLoginCommandHandler.login(loginCommand);
    }

    @Override
    public void join(UUID memberId, JoinCommand joinCommand) {
        joinCommandHandler.join(memberId, joinCommand);
    }

    @Override
    public TokenResponse test() {
        Member member = Member.builder()
                .id(new MemberId(UUID.randomUUID()))
                .nickname("imdang").build();
        log.info("memberID : {}", member.getId().getValue());
        return tokenRequestHandler.generate(member);
    }
}
