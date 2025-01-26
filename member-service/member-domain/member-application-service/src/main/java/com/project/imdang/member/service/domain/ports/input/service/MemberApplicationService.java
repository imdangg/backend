package com.project.imdang.member.service.domain.ports.input.service;

import com.project.imdang.member.service.domain.dto.DetailMyPageQuery;
import com.project.imdang.member.service.domain.dto.DetailMyPageResponse;
import com.project.imdang.member.service.domain.dto.JoinCommand;
import com.project.imdang.member.service.domain.dto.LoginResponse;
import com.project.imdang.member.service.domain.dto.MemberResponse;
import com.project.imdang.member.service.domain.dto.TokenResponse;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginCommand;
import com.project.imdang.member.service.domain.dto.oauth.OAuthWithdrawCommand;

import java.util.List;
import java.util.UUID;

public interface MemberApplicationService {
    LoginResponse login(OAuthLoginCommand loginCommand);

    DetailMyPageResponse detailMyPage(DetailMyPageQuery detailMyPageQuery);

    void join(UUID memberId, JoinCommand joinCommand);

    MemberResponse detailMember(UUID memberId);
    List<MemberResponse> listMember(List<UUID> memberIds);

    void logout(UUID memberId);

    void withdraw(UUID memberId, OAuthWithdrawCommand withdrawCommand);

    TokenResponse reissue(UUID memberId);
}
