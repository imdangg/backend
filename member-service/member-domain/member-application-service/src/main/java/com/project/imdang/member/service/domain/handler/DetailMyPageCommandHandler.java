package com.project.imdang.member.service.domain.handler;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.dto.DetailMyPageQuery;
import com.project.imdang.member.service.domain.dto.DetailMyPageResponse;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.exception.MemberNotFoundException;
import com.project.imdang.member.service.domain.mapper.MemberDataMapper;
import com.project.imdang.member.service.domain.ports.output.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DetailMyPageCommandHandler {
    private final MemberRepository memberRepository;
    private final MemberDataMapper memberDataMapper;
    public DetailMyPageResponse detailMyPage(DetailMyPageQuery detailMyPageQuery) {
        Member member = check(detailMyPageQuery.getMemberId());
        log.info("Member[id : {}] mypage is viewed", member.getId().getValue());
        return memberDataMapper.memberToDetailMyPageResponse(member);
    }

    private Member check(UUID _memberId) {
        MemberId memberId = new MemberId(_memberId);
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
    }
}
