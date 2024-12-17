package com.project.imdang.member.service.domain.ports.input.service;

import com.project.imdang.member.service.domain.dto.JoinCommand;
import com.project.imdang.member.service.domain.dto.LoginResponse;
import com.project.imdang.member.service.domain.dto.TokenResponse;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginCommand;
import com.project.imdang.member.service.domain.entity.Member;

import java.util.UUID;

public interface MemberApplicationService {
    LoginResponse login(OAuthLoginCommand loginCommand);
    void join(UUID memberId, JoinCommand joinCommand);

    TokenResponse test();
}
