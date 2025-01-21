package com.project.imdang.member.service.domain.handler.member;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.MemberDomainService;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.exception.MemberDomainException;
import com.project.imdang.member.service.domain.exception.MemberNotFoundException;
import com.project.imdang.member.service.domain.ports.output.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class LogoutCommandHandler {
    private final MemberDomainService memberDomainService;
    private final MemberRepository memberRepository;

    public void logout(UUID memberId) {
        Member member = check(memberId);
        saveMember(memberDomainService.logout(member));
        log.info("Member[id:{}] is logout", member.getId().getValue());
    }

    private Member check(UUID _memberId) {
        MemberId memberId = new MemberId(_memberId);
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
    }

    private Member saveMember(Member member) {
        Member savedMember =  memberRepository.save(member);
        if (savedMember == null) {
            String errorMessage = "Could not save Member!";
            log.error("Could not save Member!");
            throw new MemberDomainException(errorMessage);
        }
        log.info("Member[id : {}] is saved.", member.getId().getValue());
        return savedMember;
    }
}
