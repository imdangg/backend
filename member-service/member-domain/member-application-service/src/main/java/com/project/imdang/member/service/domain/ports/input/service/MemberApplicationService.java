package com.project.imdang.member.service.domain.ports.input.service;

import com.project.imdang.member.service.domain.dto.*;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginCommand;

import java.util.UUID;

import java.util.UUID;

public interface MemberApplicationService {
    LoginResponse login(OAuthLoginCommand loginCommand);

    DetailMyPageResponse detailMyPage(DetailMyPageQuery detailMyPageQuery);

    void join(UUID memberId, JoinCommand joinCommand);

    DetailMemberResponse detailMember(UUID memberId);
}
