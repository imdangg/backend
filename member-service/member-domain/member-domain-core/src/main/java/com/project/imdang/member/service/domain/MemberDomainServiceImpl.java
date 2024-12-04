package com.project.imdang.member.service.domain;

import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.valueobject.Gender;
import com.project.imdang.member.service.domain.valueobject.OAuthType;

public class MemberDomainServiceImpl implements MemberDomainService {

    @Override
    public void join(Member member, String nickname, String birthDate, Gender gender) {
        member.join(nickname, birthDate, gender);
    }

    @Override
    public Member createMember(String oAuthId, OAuthType oAuthType) {
        return Member.create(oAuthId, oAuthType);
    }
}
