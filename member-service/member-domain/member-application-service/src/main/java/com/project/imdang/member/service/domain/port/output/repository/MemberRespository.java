package com.project.imdang.member.service.domain.port.output.repository;

import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginResponse;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.valueobject.OAuthType;

import java.util.Optional;
import java.util.UUID;

public interface MemberRespository {
    boolean existByOauthIdAndType(OAuthLoginResponse loginResponse);
    Optional<Member> findMemberByOAuth(String oAuthId, OAuthType oAuthType);
    Optional<Member> findById(UUID memberId);
    Member save(Member member);
}