package com.project.imdang.member.service.domain.handler;

import com.project.imdang.member.service.domain.MemberDomainService;
import com.project.imdang.member.service.domain.dto.JoinCommand;
import com.project.imdang.member.service.domain.dto.LoginResponse;
import com.project.imdang.member.service.domain.dto.TokenResponse;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.exception.MemberDomainException;
import com.project.imdang.member.service.domain.port.output.repository.MemberRespository;
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
        Member findMember = checkMember(tokenRequestHandler.extractMemberId(accessToken));
        // TODO : 2. 닉네임 중복검사

        // 3. 회원가입
        Member member = memberDomainService.join(findMember, joinCommand.getNickname(), joinCommand.getBirthDate(), joinCommand.getGender());
        // 4. 저장
        Member joinedMember = joinMember(member);
        //5. 토큰 발급
        TokenResponse tokenResponse = tokenRequestHandler.generate(joinedMember);
        // TODO 6. 리프레쉬 토큰 저장
        tokenRequestHandler.storeRefreshToken(member.getOAuthId(), tokenResponse.getRefreshToken());
        return LoginResponse.from(tokenResponse, true);
    }

    private Member joinMember(Member member) {
        Member savedMember =  memberRespository.save(member);
        if (savedMember == null) {
            String errorMessage = "Could not join Member!";
            log.error("Could not join Member!");
            throw new MemberDomainException(errorMessage);
        }
        log.info("Member[id : {}] is Joined !", member.getMemberId().getValue());
        return savedMember;
    }
    private Member checkMember(UUID memberId) {
        //TODO : 예외
        Member findMember = memberRespository.findById(memberId)
                .orElseThrow(() ->new IllegalArgumentException());
        log.info("Member[id : {}] is Login", findMember.getMemberId().getValue());
        return findMember;
    }
}