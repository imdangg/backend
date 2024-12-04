package com.project.imdang.member.service.domain.handler;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.MemberDomainService;
import com.project.imdang.member.service.domain.dto.JoinCommand;
import com.project.imdang.member.service.domain.dto.LoginResponse;
import com.project.imdang.member.service.domain.dto.TokenResponse;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.exception.MemberNotFoundException;
import com.project.imdang.member.service.domain.ports.output.MemberRespository;
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
    private final MemberRespository memberRespository;

    public LoginResponse join(String accessToken, JoinCommand joinCommand) {
        // 1. 토큰에서 유저 정보 추출 후 검증
        Member member = checkMember(tokenRequestHandler.extractMemberId(accessToken));
        // TODO : 2. 닉네임 중복검사

        // TODO - REVIEW
        // 3. 회원가입 (입력 정보 업데이트)
        // 4. 저장
        memberDomainService.join(member, joinCommand.getNickname(), joinCommand.getBirthDate(), joinCommand.getGender());
        // 5. 토큰 발급
        TokenResponse tokenResponse = tokenRequestHandler.generate(member);
        // TODO 6. 리프레쉬 토큰 저장
        tokenRequestHandler.storeRefreshToken(member.getOAuthId(), tokenResponse.getRefreshToken());
        return LoginResponse.from(tokenResponse);
    }

    private Member checkMember(UUID _memberId) {
        MemberId memberId = new MemberId(_memberId);
        Member findMember = memberRespository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
        log.info("Member[id : {}] logged in.", findMember.getId().getValue());
        return findMember;
    }
}
