package com.project.imdang.member.service.domain.handler.auth;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.MemberDomainService;
import com.project.imdang.member.service.domain.dto.TokenResponse;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.exception.MemberDomainException;
import com.project.imdang.member.service.domain.exception.MemberNotFoundException;
import com.project.imdang.member.service.domain.ports.output.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReissueCommandHandler {
    private final MemberRepository memberRepository;
    private final MemberDomainService memberDomainService;
    private final TokenRequestHandler tokenRequestHandler;

    public TokenResponse reissue(UUID memberId) {
        Member member = check(memberId);
        TokenResponse tokenResponse = tokenRequestHandler.generate(member);
        log.info("Member[id:{}] token reissued", member.getId().getValue());
        saveMember(memberDomainService.storeRefreshToken(member, tokenResponse.getRefreshToken()));
        return tokenResponse;
    }

    private Member check(UUID _memberId) {
        MemberId memberId = new MemberId(_memberId);
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
        return findMember;
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
