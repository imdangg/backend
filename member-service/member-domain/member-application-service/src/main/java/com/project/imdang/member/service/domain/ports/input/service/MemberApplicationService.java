package com.project.imdang.member.service.domain.ports.input.service;

import com.project.imdang.member.service.domain.dto.DetailMyPageQuery;
import com.project.imdang.member.service.domain.dto.DetailMyPageResponse;
import com.project.imdang.member.service.domain.dto.JoinCommand;
import com.project.imdang.member.service.domain.dto.LoginResponse;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginCommand;
import com.project.imdang.member.service.domain.entity.Member;

import java.util.UUID;

public interface MemberApplicationService {
    LoginResponse login(OAuthLoginCommand loginCommand);
    void join(String accessToken, JoinCommand joinCommand);

    DetailMyPageResponse detailMyPage(DetailMyPageQuery detailMyPageQuery);
}
