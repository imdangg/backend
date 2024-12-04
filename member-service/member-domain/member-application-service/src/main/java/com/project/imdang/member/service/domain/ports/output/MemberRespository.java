package com.project.imdang.member.service.domain.ports.output;

import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginResponse;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.valueobject.OAuthType;

import java.util.Optional;
import java.util.UUID;

public interface MemberRespository {
//    boolean existByOauthIdAndType(OAuthLoginResponse loginResponse);
    Optional<Member> findMemberByOAuthIdAndOAuthType(String oAuthId, OAuthType oAuthType);
    Optional<Member> findById(MemberId memberId);
    Member save(Member member);
}
