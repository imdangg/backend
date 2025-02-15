package com.project.imdang.member.service.domain.handler.auth;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.MemberDomainService;
import com.project.imdang.member.service.domain.dto.TokenReissueCommand;
import com.project.imdang.member.service.domain.dto.TokenResponse;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.exception.MemberDomainException;
import com.project.imdang.member.service.domain.handler.MemberHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReissueCommandHandler {
    private final MemberDomainService memberDomainService;
    private final MemberHelper memberHelper;
    private final TokenRequestHandler tokenRequestHandler;

    @Transactional
    public TokenResponse reissue(TokenReissueCommand tokenReissueCommand) {
        Member member = check(tokenReissueCommand.getMemberId());
        validate(tokenReissueCommand.getRefreshToken(), member);
        TokenResponse tokenResponse = tokenRequestHandler.generate(member);
        log.info("Member[id: {}] token is reissued.", member.getId().getValue());
        memberHelper.save(memberDomainService.storeRefreshToken(member, tokenResponse.getRefreshToken()));
        return tokenResponse;
    }

    private void validate(String refreshToken, Member member) {
        // 1. 유효한 리프레쉬인지
        tokenRequestHandler.validateRefreshToken(refreshToken);
        // 2. 일치하는 리프레쉬토큰인지
        if (!member.getRefreshToken().equals(refreshToken)) {
            //TODO : 보완
            throw new MemberDomainException("Invalid Token");
        }
    }

    private Member check(UUID _memberId) {
        MemberId memberId = new MemberId(_memberId);
        return memberHelper.get(memberId);
    }
}
