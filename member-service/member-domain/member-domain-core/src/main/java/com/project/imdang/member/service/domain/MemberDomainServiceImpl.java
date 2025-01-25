package com.project.imdang.member.service.domain;

import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.domain.valueobject.Gender;
import com.project.imdang.member.service.domain.valueobject.OAuthType;

public class MemberDomainServiceImpl implements MemberDomainService {

    @Override
    public Member join(Member member, String nickname, String birthDate, Gender gender, String deviceToken) {
        member.join(nickname, birthDate, gender, deviceToken);
        return member;
    }

    @Override
    public Member createMember(String oAuthId, OAuthType oAuthType) {
        return Member.createNewMember(oAuthId, oAuthType);
    }

    @Override
    public Member logout(Member member) {
        member.logout();
        return member;
    }

    @Override
    public Member withdraw(Member member) {
        member.withdraw();
        return member;
    }

    @Override
    public Member storeRefreshToken(Member member, String refreshToken) {
        member.storeRefeashToken(refreshToken);
        return member;
    }
}
