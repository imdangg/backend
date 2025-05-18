package com.project.imdang.member.domain.ports.output.repository;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.entity.Member;
import com.project.imdang.common.domain.valueobject.OAuthType;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Optional<Member> findByOAuthIdAndOAuthTypeAndIsDeleted(String oAuthId, OAuthType oAuthType, Boolean isDeleted);
    Optional<Member> findById(MemberId memberId);
    List<Member> findAllByIds(List<MemberId> memberIds);
    Optional<Member> findByNickname(String nickname);
    Optional<Member> findByIdAndIsDeleted(MemberId memberId);
    Member save(Member member);
}
