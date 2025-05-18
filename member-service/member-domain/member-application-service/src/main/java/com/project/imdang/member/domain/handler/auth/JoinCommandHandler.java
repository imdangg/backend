package com.project.imdang.member.domain.handler.auth;

import com.project.imdang.common.domain.exception.DomainException;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.MemberDomainService;
import com.project.imdang.member.domain.dto.auth.JoinMemberCommand;
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
    public void join(JoinMemberCommand joinMemberCommand) {
        // 1. 토큰에서 유저 정보 추출 후 검증
        MemberId memberId = joinMemberCommand.getMemberId();
        Member member = memberHelper.get(memberId);
        // 2. 닉네임 중복검사
        checkDuplicateNickname(joinMemberCommand.getNickname());
        // 3. 회원가입 (입력 정보 업데이트)
        Member updatedMember = memberDomainService.join(member, joinMemberCommand.getNickname(), joinMemberCommand.getBirthDate(), joinMemberCommand.getGender(), joinMemberCommand.getDeviceToken());
        // 4. 저장
        memberHelper.save(updatedMember);
    }

    private void checkDuplicateNickname(String nickname) {
         if (memberHelper.getByNickname(nickname).isPresent()) {
             String errorMessage = "Nickname is already used!";
             log.error(errorMessage);
             throw new DomainException(errorMessage);
         }
    }
}
