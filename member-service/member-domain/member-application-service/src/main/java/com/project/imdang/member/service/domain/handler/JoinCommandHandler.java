package com.project.imdang.member.service.domain.handler;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.MemberDomainService;
import com.project.imdang.member.service.domain.dto.JoinCommand;
import com.project.imdang.domain.entity.Member;
import com.project.imdang.member.service.domain.exception.MemberDomainException;
import com.project.imdang.member.service.domain.exception.MemberNotFoundException;
import com.project.imdang.domain.ports.output.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class JoinCommandHandler {

    private final MemberDomainService memberDomainService;
    private final TokenRequestHandler tokenRequestHandler;
    private final MemberRepository memberRepository;

    public void join(String accessToken, JoinCommand joinCommand) {
        // 1. 토큰에서 유저 정보 추출 후 검증
        Member member = checkMember(tokenRequestHandler.extractMemberId(accessToken));
        // TODO : 2. 닉네임 중복검사

        // 3. 회원가입 (입력 정보 업데이트)
        Member updatedMember = memberDomainService.join(member, joinCommand.getNickname(), joinCommand.getBirthDate(), joinCommand.getGender());
        // 4. 저장
        saveMember(updatedMember);
    }

    private Member checkMember(UUID _memberId) {
        MemberId memberId = new MemberId(_memberId);
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
        log.info("Member[id : {}] logged in.", findMember.getId().getValue());
        return findMember;
    }

    private Member saveMember(Member member) {
        Member savedMember =  memberRepository.save(member);
        if (savedMember == null) {
            String errorMessage = "Could not save Member!";
            log.error("Could not save Member!");
            throw new MemberDomainException(errorMessage);
        }
        log.info("Member[id : {}] is created.", member.getId().getValue());
        return savedMember;
    }
}
