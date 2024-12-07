package com.project.imdang.member.service.domain;

import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.valueobject.Gender;
import com.project.imdang.member.service.domain.valueobject.OAuthType;

public interface MemberDomainService {

    Member join(Member member, String nickname, String birthDate, Gender gender);

    Member createMember(String id, OAuthType oAuthType);
}
