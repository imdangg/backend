package com.project.imdang.member.service.domain.ports.output;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.valueobject.OAuthType;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Optional<Member> findByOAuthIdAndOAuthType(String oAuthId, OAuthType oAuthType);
    Optional<Member> findById(MemberId memberId);
    List<Member> findAllByIds(List<MemberId> memberIds);

    Optional<Member> findByNickname(String nickname);
    Member save(Member member);
}
