package com.project.imdang.member.domain.handler.member;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.dto.member.MemberResult;
import com.project.imdang.member.domain.mapper.MemberDataMapper;
import com.project.imdang.member.domain.ports.output.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class ListMemberQueryHandler {

    private final MemberDataMapper memberDataMapper;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MemberResult> listMember(List<MemberId> memberIds) {
        return memberRepository.findAllByIds(memberIds).stream()
                .map(memberDataMapper::memberToDetailMemberResponse)
                .collect(Collectors.toList());
    }
}
