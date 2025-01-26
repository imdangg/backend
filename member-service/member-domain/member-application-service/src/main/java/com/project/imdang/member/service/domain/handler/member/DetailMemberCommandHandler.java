package com.project.imdang.member.service.domain.handler.member;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.dto.MemberResponse;
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
public class DetailMemberCommandHandler {

    private final MemberDataMapper memberDataMapper;
    private final MemberRepository memberRepository;

    public MemberResponse detailMember(UUID _memberId) {
        MemberId memberId = new MemberId(_memberId);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
        return memberDataMapper.memberToDetailMemberResponse(member);
    }
}
