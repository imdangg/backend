package com.project.imdang.member.domain.handler.auth;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.MemberDomainService;
import com.project.imdang.member.domain.dto.auth.ReissueTokenCommand;
import com.project.imdang.member.domain.dto.auth.TokenResult;
import com.project.imdang.member.domain.entity.Member;
import com.project.imdang.member.domain.exception.MemberDomainException;
import com.project.imdang.member.domain.handler.MemberHelper;
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

    @Transactional
    public TokenResult reissue(ReissueTokenCommand reissueTokenCommand) {

        final MemberId memberId = reissueTokenCommand.getMemberId();
        Member member = memberHelper.get(memberId);

        String refreshToken = reissueTokenCommand.getRefreshToken();
        boolean validated = tokenHandler.validateToken(refreshToken);
        if (validated ||
                (!member.getRefreshToken().equals(refreshToken))){
            throw new MemberDomainException("Invalid Token");
        }

        TokenResult tokenResult = tokenHandler.generateToken(memberId);
        log.info("Member[id: {}] token is reissued.", member.getId().getValue());
        memberHelper.save(memberDomainService.storeRefreshToken(member, tokenResult.getRefreshToken()));
        return tokenResult;
    }
}
