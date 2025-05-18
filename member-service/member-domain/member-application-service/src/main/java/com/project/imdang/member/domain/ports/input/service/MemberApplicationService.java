package com.project.imdang.member.domain.ports.input.service;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.dto.member.MemberResult;
import com.project.imdang.member.domain.dto.member.MyPageInfoResult;
import com.project.imdang.member.domain.dto.member.OAuthWithdrawCommand;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface MemberApplicationService {

    void logout(MemberId memberId);
    void withdraw(OAuthWithdrawCommand withdrawCommand);

    List<MemberResult> listMember(List<MemberId> memberIds);
    MemberResult detailMember(MemberId memberId);
    MyPageInfoResult detailMyPage(MemberId memberId);

    void accuseMember(MemberId memberId);

    @Async
    void increaseInsightCount(MemberId memberId);
    void decreaseInsightCount(MemberId memberId);
}
