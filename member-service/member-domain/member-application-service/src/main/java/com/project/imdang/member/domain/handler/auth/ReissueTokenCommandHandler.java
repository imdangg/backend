package com.project.imdang.member.domain.handler.auth;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.MemberDomainService;
import com.project.imdang.member.domain.dto.login.ReissueTokenCommand;
import com.project.imdang.member.domain.dto.login.TokenResult;
import com.project.imdang.member.domain.entity.Member;
import com.project.imdang.member.domain.exception.MemberDomainException;
import com.project.imdang.member.domain.handler.MemberHelper;
import com.project.imdang.member.domain.ports.output.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReissueTokenCommandHandler {
    private final MemberDomainService memberDomainService;
    private final MemberHelper memberHelper;
    private final TokenHandler tokenHandler;
    private final TokenProvider tokenProvider;

    @Transactional
    public TokenResult reissue(ReissueTokenCommand reissueTokenCommand) {

        final MemberId memberId = reissueTokenCommand.getMemberId();
        final String refreshToken = reissueTokenCommand.getRefreshToken();
        tokenProvider.validate(refreshToken);

        Member member = memberHelper.get(memberId);
        if (!member.getRefreshToken().equals(refreshToken)) {
            throw new MemberDomainException("Invalid Token!");
        }

        TokenResult tokenResult = tokenHandler.generateToken(memberId);
        log.info("Member[id: {}] token is reissued.", member.getId().getValue());
        memberHelper.save(memberDomainService.storeRefreshToken(member, tokenResult.getRefreshToken()));
        return tokenResult;
    }
}
