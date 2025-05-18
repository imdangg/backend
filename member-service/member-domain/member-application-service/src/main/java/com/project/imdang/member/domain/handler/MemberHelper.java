package com.project.imdang.member.domain.handler;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.entity.Member;
import com.project.imdang.member.domain.exception.MemberDomainException;
import com.project.imdang.member.domain.exception.MemberNotFoundException;
import com.project.imdang.member.domain.ports.output.repository.MemberRepository;
import com.project.imdang.common.domain.valueobject.OAuthType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberHelper {

    private final MemberRepository memberRepository;

    public Member get(MemberId memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
    }

    public Optional<Member> getByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

    public Optional<Member> getByOAuthIdAndOAuthTypeAndIsDeleted(String oAuthId, OAuthType oAuthType, Boolean isDeleted) {
        return memberRepository.findByOAuthIdAndOAuthTypeAndIsDeleted(oAuthId, oAuthType, isDeleted);
    }

    public Member save(Member member) {
        Member savedMember =  memberRepository.save(member);
        if (savedMember == null) {
            String errorMessage = "Could not save member!";
            log.error(errorMessage);
            throw new MemberDomainException(errorMessage);
        }
        log.info("Member[id : {}] is saved.", member.getId().getValue());
        return savedMember;
    }

    public Member getByIdAndIsDeleted(MemberId memberId) {
        return memberRepository.findByIdAndIsDeleted(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
    }
}
