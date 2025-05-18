package com.project.imdang.member.domain.handler.member;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.dto.member.MemberResult;
import com.project.imdang.member.domain.entity.Member;
import com.project.imdang.member.domain.handler.MemberHelper;
import com.project.imdang.member.domain.mapper.MemberDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class DetailMemberQueryHandler {

    private final MemberDataMapper memberDataMapper;
    private final MemberHelper memberHelper;

    @Transactional(readOnly = true)
    public MemberResult detailMember(MemberId memberId) {
        Member member = memberHelper.get(memberId);
        return memberDataMapper.memberToDetailMemberResponse(member);
    }
}
