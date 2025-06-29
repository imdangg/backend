package com.project.imdang.member.domain;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.dto.member.JoinCommand;
import com.project.imdang.member.domain.dto.member.MemberResult;
import com.project.imdang.member.domain.dto.member.MyPageInfoResult;
import com.project.imdang.member.domain.dto.member.WithdrawCommand;
import com.project.imdang.member.domain.handler.member.*;
import com.project.imdang.member.domain.ports.input.service.MemberApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberApplicationServiceImpl implements MemberApplicationService {

    private final WithdrawCommandHandler withdrawCommandHandler;
    private final JoinCommandHandler joinCommandHandler;
    private final ListMemberQueryHandler listMemberQueryHandler;
    private final DetailMemberQueryHandler detailMemberQueryHandler;
    private final DetailMyPageInfoQueryHandler detailMyPageInfoQueryHandler;

    private final AccuseMemberCommandHandler accuseMemberCommandHandler;
    private final UpdateMemberCommandHandler updateMemberCommandHandler;

    @Override
    public MyPageInfoResult detailMyPage(MemberId memberId) {
        return detailMyPageInfoQueryHandler.detailMyPage(memberId);
    }

    @Override
    public MemberResult detailMember(MemberId memberId) {
        return detailMemberQueryHandler.detailMember(memberId);
    }

    @Override
    public List<MemberResult> listMember(List<MemberId> memberIds) {
        return listMemberQueryHandler.listMember(memberIds);
    }

    @Override
    public Boolean join(JoinCommand joinCommand) {
        return joinCommandHandler.join(joinCommand);
    }

    @Override
    public Boolean withdraw(WithdrawCommand withdrawCommand) {
        return withdrawCommandHandler.withdraw(withdrawCommand);
    }

    @Override
    public Boolean accuseMember(MemberId memberId) {
        return accuseMemberCommandHandler.accuse(memberId);
    }

    @Override
    public Boolean updateMember(MemberId memberId) {
        return updateMemberCommandHandler.update(memberId);
    }
}
