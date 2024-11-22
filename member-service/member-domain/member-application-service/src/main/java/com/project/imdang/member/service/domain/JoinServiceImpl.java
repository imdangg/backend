package com.project.imdang.member.service.domain;

import com.project.imdang.member.service.domain.dto.JoinCommand;
import com.project.imdang.member.service.domain.dto.TokenResponse;
import com.project.imdang.member.service.domain.dto.LoginResponse;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.handler.JoinCommandHandler;
import com.project.imdang.member.service.domain.handler.TokenRequestHandler;
import com.project.imdang.member.service.domain.port.input.service.JoinService;
import com.project.imdang.member.service.domain.port.output.repository.MemberRespository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService {

    private final TokenRequestHandler tokenRequestHandler;
    private final JoinCommandHandler joinCommandHandler;
    private final MemberRespository memberRespository;
    @Override
    public LoginResponse join(String accessToken, JoinCommand joinCommand) {
        // 1. 토큰에서 유저 정보 추출
        Member findMember = memberRespository.findById(tokenRequestHandler.extractMemberId(accessToken));
        // TODO : 2. 닉네임 중복검사

        // 3. 회원가입
        Member member = joinCommandHandler.joinMember(findMember, joinCommand);
        // 4. 저장
        Member joined = memberRespository.join(member);
        //5. 토큰 발급
        TokenResponse tokenResponse = tokenRequestHandler.generate(joined);
        // TODO 6. 리프레쉬 토큰 저장
        tokenRequestHandler.storeRefreshToken(member.getOAuthId(), tokenResponse.getRefreshToken());

        return LoginResponse.from(tokenResponse, true);
    }
}