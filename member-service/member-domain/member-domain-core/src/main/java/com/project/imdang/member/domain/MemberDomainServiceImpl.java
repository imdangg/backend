package com.project.imdang.member.domain;

import com.project.imdang.common.domain.valueobject.Gender;
import com.project.imdang.common.domain.valueobject.OAuthProvider;
import com.project.imdang.member.domain.entity.Member;
import com.project.imdang.member.domain.valueobject.AccusePenaltyPolicy;

public class MemberDomainServiceImpl implements MemberDomainService {

    @Override
    public Member join(Member member, String nickname, String birthDate, Gender gender, String deviceToken) {
        member.join(nickname, birthDate, gender, deviceToken);
        return member;
    }

    @Override
    public Member createMember(String oAuthId, OAuthProvider oAuthProvider) {
        return Member.createNewMember(oAuthId, oAuthProvider);
    }

    @Override
    public Member accuseMember(Member member, AccusePenaltyPolicy accusePenaltyPolicy) {
        member.accuse(accusePenaltyPolicy);
        return member;
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
        member.storeRefreshToken(refreshToken);
        return member;
    }
}
