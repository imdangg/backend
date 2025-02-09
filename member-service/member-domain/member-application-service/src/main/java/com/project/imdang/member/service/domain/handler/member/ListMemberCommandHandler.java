package com.project.imdang.member.service.domain.handler.member;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.dto.MemberResponse;
import com.project.imdang.member.service.domain.mapper.MemberDataMapper;
import com.project.imdang.member.service.domain.ports.output.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class ListMemberCommandHandler {

    private final MemberDataMapper memberDataMapper;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MemberResponse> listMember(List<UUID> _memberIds) {
        List<MemberId> memberIds = _memberIds.stream()
                .map(MemberId::new)
                .toList();
        return memberRepository.findAllByIds(memberIds).stream()
                .map(memberDataMapper::memberToDetailMemberResponse)
                .collect(Collectors.toList());
    }
}
