package com.project.imdang.member.service.domain.handler;

import com.project.imdang.member.service.domain.MemberDomainService;
import com.project.imdang.member.service.domain.dto.JoinCommand;
import com.project.imdang.member.service.domain.entity.Member;


public class JoinCommandHandler {

    private MemberDomainService memberDomainService;

    public Member joinMember(Member member, JoinCommand command) {
        // TODO: memberDomainService를 어떻게 활용할 수 있을까?
        return member.join(command.getNickname(), command.getBirthDate(), command.getGender());
    }
}