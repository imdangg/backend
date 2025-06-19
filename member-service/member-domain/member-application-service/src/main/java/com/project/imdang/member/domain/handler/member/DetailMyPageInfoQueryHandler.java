package com.project.imdang.member.domain.handler.member;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.dto.member.MyPageInfoResult;
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
public class DetailMyPageInfoQueryHandler {

    private final MemberHelper memberHelper;
    private final MemberDataMapper memberDataMapper;

    @Transactional(readOnly = true)
    public MyPageInfoResult detailMyPage(MemberId memberId) {
        Member member = memberHelper.get(memberId);
        log.info("MyPage of Member[id : {}] is viewed.", member.getId().getValue());
        return memberDataMapper.memberToDetailMyPageResponse(member);
    }
}
