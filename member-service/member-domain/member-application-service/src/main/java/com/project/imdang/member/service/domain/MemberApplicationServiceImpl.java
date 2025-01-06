package com.project.imdang.member.service.domain;

import com.project.imdang.member.service.domain.dto.DetailMyPageQuery;
import com.project.imdang.member.service.domain.dto.DetailMyPageResponse;
import com.project.imdang.member.service.domain.dto.JoinCommand;
import com.project.imdang.member.service.domain.dto.LoginResponse;
import com.project.imdang.member.service.domain.dto.TokenResponse;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginCommand;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.handler.DetailMyPageCommandHandler;
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
    private final DetailMyPageCommandHandler detailMyPageCommandHandler;

    @Override
    public LoginResponse login(OAuthLoginCommand loginCommand) {
        return oAuthLoginCommandHandler.login(loginCommand);
    }

    @Override
    public void join(UUID memberId, JoinCommand joinCommand) {
        joinCommandHandler.join(memberId, joinCommand);
    }


    @Override
    public DetailMyPageResponse detailMyPage(DetailMyPageQuery detailMyPageQuery) {
        return detailMyPageCommandHandler.detailMyPage(detailMyPageQuery);
    }
}
