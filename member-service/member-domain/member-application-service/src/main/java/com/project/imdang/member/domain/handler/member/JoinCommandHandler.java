package com.project.imdang.member.domain.handler.member;

import com.project.imdang.common.domain.exception.DomainException;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.MemberDomainService;
import com.project.imdang.member.domain.dto.member.JoinCommand;
import com.project.imdang.member.domain.entity.Member;
import com.project.imdang.member.domain.handler.MemberHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class JoinCommandHandler {

    private final MemberDomainService memberDomainService;
    private final MemberHelper memberHelper;

    @Transactional
    public Boolean join(JoinCommand joinCommand) {
        // 1. 토큰에서 유저 정보 추출 후 검증
        final MemberId memberId = joinCommand.getMemberId();
        Member member = memberHelper.get(memberId);
        // 2. 닉네임 중복검사
        checkDuplicateNickname(joinCommand.getNickname());
        // 3. 회원가입 (입력 정보 업데이트)
        Member updatedMember = memberDomainService.join(member, joinCommand.getNickname(), joinCommand.getBirthDate(), joinCommand.getGender(), joinCommand.getDeviceToken());
        // 4. 저장
        memberHelper.save(updatedMember);
        return true;
    }

    private void checkDuplicateNickname(String nickname) {
         if (memberHelper.getByNickname(nickname).isPresent()) {
             String errorMessage = "Nickname is already used!";
             log.error(errorMessage);
             throw new DomainException(errorMessage);
         }
    }
}
