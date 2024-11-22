package com.project.imdang.member.service.domain.port.output.repository;

import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginResponse;
import com.project.imdang.member.service.domain.entity.Member;

import java.util.UUID;

public interface MemberRespository {
    boolean existByOauthIdAndType(OAuthLoginResponse loginResponse);
    Member findOrCreateMember(OAuthLoginResponse loginResponse);
    Member findById(UUID memberId);
    Member join(Member member);
}