package com.project.imdang.member.service.domain.ports.input.service;

import com.project.imdang.member.service.domain.dto.DetailMyPageQuery;
import com.project.imdang.member.service.domain.dto.DetailMyPageResponse;
import com.project.imdang.member.service.domain.dto.JoinCommand;
import com.project.imdang.member.service.domain.dto.LoginResponse;
import com.project.imdang.member.service.domain.dto.TokenResponse;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginCommand;

import java.util.UUID;

import java.util.UUID;

public interface MemberApplicationService {
    LoginResponse login(OAuthLoginCommand loginCommand);

    DetailMyPageResponse detailMyPage(DetailMyPageQuery detailMyPageQuery);

    void join(UUID memberId, JoinCommand joinCommand);
}
