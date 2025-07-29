package com.project.imdang.member.domain.ports.input.service;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.dto.member.*;

import java.util.List;

public interface MemberApplicationService {

    MyPageInfoResult detailMyPage(MemberId memberId);
    MemberResult detailMember(MemberId memberId);

    List<MemberResult> listMember(List<MemberId> memberIds);

    Boolean join(JoinCommand joinCommand);
    Boolean withdraw(WithdrawCommand withdrawCommand);
    Boolean accuseMember(MemberId memberId);
    Boolean updateMember(MemberId memberId);

    Boolean condition(ConditionCommand conditionCommand, PriorityCommand priorityCommand, InterestDistrictCommand interestDistrictCommand);
}
