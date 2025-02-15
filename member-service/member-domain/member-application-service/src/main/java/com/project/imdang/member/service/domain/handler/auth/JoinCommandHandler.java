package com.project.imdang.member.service.domain.handler.auth;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.MemberDomainService;
import com.project.imdang.member.service.domain.dto.JoinCommand;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.handler.MemberHelper;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class JoinCommandHandler {

    private final MemberDomainService memberDomainService;
    private final MemberHelper memberHelper;

    @Transactional
    public void join(UUID memberId, JoinCommand joinCommand) {
        // 1. 토큰에서 유저 정보 추출 후 검증
        Member member = checkMember(memberId);
        // 2. 닉네임 중복검사
        checkDuplicateNickname(joinCommand.getNickname());
        // 3. 회원가입 (입력 정보 업데이트)
        Member updatedMember = memberDomainService.join(member, joinCommand.getNickname(), joinCommand.getBirthDate(), joinCommand.getGender(), joinCommand.getDeviceToken());
        // 4. 저장
        memberHelper.save(updatedMember);
    }

    private Member checkMember(UUID _memberId) {
        MemberId memberId = new MemberId(_memberId);
        Member member = memberHelper.get(memberId);
        log.info("Member[id : {}] logged in.", member.getId().getValue());
        return member;
    }

    private void checkDuplicateNickname(String nickname) {
         if (memberHelper.getByNickname(nickname).isPresent()) {
             String errorMessage = "Nickname is already used!";
             log.error(errorMessage);
             throw new ConstraintViolationException(errorMessage, null);
         }
    }
}
