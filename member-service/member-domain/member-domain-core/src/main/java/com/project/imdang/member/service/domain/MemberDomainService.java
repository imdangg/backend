package com.project.imdang.member.service.domain;

import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.valueobject.OAuthType;

public interface MemberDomainService {

    Member join(Member findMember, String nickname, String birthDate, String gender);

    Member createMember(String id, OAuthType oAuthType);
}