package com.project.imdang.member.service.domain.handler.member;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.dto.DetailMyPageQuery;
import com.project.imdang.member.service.domain.dto.DetailMyPageResponse;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.handler.MemberHelper;
import com.project.imdang.member.service.domain.mapper.MemberDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class DetailMyPageCommandHandler {

    private final MemberHelper memberHelper;
    private final MemberDataMapper memberDataMapper;

    @Transactional(readOnly = true)
    public DetailMyPageResponse detailMyPage(DetailMyPageQuery detailMyPageQuery) {

        MemberId memberId = new MemberId(detailMyPageQuery.getMemberId());
        Member member = memberHelper.get(memberId);
        log.info("MyPage of Member[id : {}] is viewed.", member.getId().getValue());
        return memberDataMapper.memberToDetailMyPageResponse(member);
    }
}
