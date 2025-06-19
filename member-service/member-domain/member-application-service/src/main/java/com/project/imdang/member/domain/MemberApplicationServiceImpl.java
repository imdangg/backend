package com.project.imdang.member.domain;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.dto.member.MemberResult;
import com.project.imdang.member.domain.dto.member.MyPageInfoResult;
import com.project.imdang.member.domain.dto.member.OAuthWithdrawCommand;
import com.project.imdang.member.domain.handler.auth.LogoutCommandHandler;
import com.project.imdang.member.domain.handler.auth.WithdrawCommandHandler;
import com.project.imdang.member.domain.handler.member.AccuseMemberCommandHandler;
import com.project.imdang.member.domain.handler.member.DetailMemberQueryHandler;
import com.project.imdang.member.domain.handler.member.DetailMyPageInfoQueryHandler;
import com.project.imdang.member.domain.handler.member.ListMemberQueryHandler;
import com.project.imdang.member.domain.handler.member.UpdateInsightCountCommandHandler;
import com.project.imdang.member.domain.ports.input.service.MemberApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberApplicationServiceImpl implements MemberApplicationService {

    private final LogoutCommandHandler logoutCommandHandler;
    private final WithdrawCommandHandler withdrawCommandHandler;

    private final ListMemberQueryHandler listMemberQueryHandler;
    private final DetailMemberQueryHandler detailMemberQueryHandler;
    private final DetailMyPageInfoQueryHandler detailMyPageInfoQueryHandler;

    private final AccuseMemberCommandHandler accuseMemberCommandHandler;
    private final UpdateInsightCountCommandHandler updateInsightCountCommandHandler;

    @Override
    public void logout(MemberId memberId) {
        logoutCommandHandler.logout(memberId);
    }

    @Override
    public void withdraw(OAuthWithdrawCommand withdrawCommand) {
        withdrawCommandHandler.withdraw(withdrawCommand);
    }

    @Override
    public List<MemberResult> listMember(List<MemberId> memberIds) {
        return listMemberQueryHandler.listMember(memberIds);
    }

    @Override
    public MemberResult detailMember(MemberId memberId) {
        return detailMemberQueryHandler.detailMember(memberId);
    }

    @Override
    public MyPageInfoResult detailMyPage(MemberId memberId) {
        return detailMyPageInfoQueryHandler.detailMyPage(memberId);
    }

    @Override
    public void accuseMember(MemberId memberId) {
        accuseMemberCommandHandler.accuse(memberId);
    }

    @Override
    public void increaseInsightCount(MemberId memberId) {
        updateInsightCountCommandHandler.increaseInsightCount(memberId);
    }

    @Override
    public void decreaseInsightCount(MemberId memberId) {
        updateInsightCountCommandHandler.decreaseInsightCount(memberId);
    }
}
