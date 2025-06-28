package com.project.imdang.member.domain.ports.input.service;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.dto.member.JoinCommand;
import com.project.imdang.member.domain.dto.member.WithdrawCommand;
import com.project.imdang.member.domain.dto.member.MemberResult;
import com.project.imdang.member.domain.dto.member.MyPageInfoResult;

import java.util.List;

public interface MemberApplicationService {

    MyPageInfoResult detailMyPage(MemberId memberId);
    MemberResult detailMember(MemberId memberId);

    List<MemberResult> listMember(List<MemberId> memberIds);

    Boolean join(JoinCommand joinCommand);
    Boolean withdraw(WithdrawCommand withdrawCommand);
    Boolean accuseMember(MemberId memberId);
}
