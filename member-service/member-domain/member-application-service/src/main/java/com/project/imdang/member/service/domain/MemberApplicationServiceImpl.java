package com.project.imdang.member.service.domain;

import com.project.imdang.member.service.domain.dto.DetailMyPageQuery;
import com.project.imdang.member.service.domain.dto.DetailMyPageResponse;
import com.project.imdang.member.service.domain.dto.JoinCommand;
import com.project.imdang.member.service.domain.dto.LoginResponse;
import com.project.imdang.member.service.domain.dto.MemberResponse;
import com.project.imdang.member.service.domain.dto.TokenResponse;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginCommand;
import com.project.imdang.member.service.domain.dto.oauth.OAuthWithdrawCommand;
import com.project.imdang.member.service.domain.handler.auth.JoinCommandHandler;
import com.project.imdang.member.service.domain.handler.auth.OAuthLoginCommandHandler;
import com.project.imdang.member.service.domain.handler.auth.ReissueCommandHandler;
import com.project.imdang.member.service.domain.handler.member.DetailMemberCommandHandler;
import com.project.imdang.member.service.domain.handler.member.DetailMyPageCommandHandler;
import com.project.imdang.member.service.domain.handler.member.ListMemberCommandHandler;
import com.project.imdang.member.service.domain.handler.member.LogoutCommandHandler;
import com.project.imdang.member.service.domain.handler.member.WithdrawCommandHandler;
import com.project.imdang.member.service.domain.ports.input.service.MemberApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberApplicationServiceImpl implements MemberApplicationService {

    private final OAuthLoginCommandHandler oAuthLoginCommandHandler;
    private final JoinCommandHandler joinCommandHandler;
    private final DetailMyPageCommandHandler detailMyPageCommandHandler;
    private final DetailMemberCommandHandler detailMemberCommandHandler;
    private final ListMemberCommandHandler listMemberCommandHandler;

    private final LogoutCommandHandler logoutCommandHandler;
    private final WithdrawCommandHandler withdrawCommandHandler;
    private final ReissueCommandHandler reissueCommandHandler;

    @Override
    public LoginResponse login(OAuthLoginCommand loginCommand) {
        return oAuthLoginCommandHandler.login(loginCommand);
    }

    @Override
    public void join(UUID memberId, JoinCommand joinCommand) {
        joinCommandHandler.join(memberId, joinCommand);
    }

    @Override
    public MemberResponse detailMember(UUID memberId) {
        return detailMemberCommandHandler.detailMember(memberId);
    }

    @Override
    public List<MemberResponse> listMember(List<UUID> memberIds) {
        return listMemberCommandHandler.listMember(memberIds);
    }

    @Override
    public DetailMyPageResponse detailMyPage(DetailMyPageQuery detailMyPageQuery) {
        return detailMyPageCommandHandler.detailMyPage(detailMyPageQuery);
    }

    @Override
    public void logout(UUID memberId) {
        logoutCommandHandler.logout(memberId);
    }

    @Override
    public void withdraw(UUID memberId, OAuthWithdrawCommand withdrawCommand) {
        withdrawCommandHandler.withdraw(memberId, withdrawCommand);
    }

    @Override
    public TokenResponse reissue(UUID memberId) {
        return reissueCommandHandler.reissue(memberId);
    }
}
