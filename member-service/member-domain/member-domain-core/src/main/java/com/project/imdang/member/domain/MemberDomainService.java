package com.project.imdang.member.domain;


import com.project.imdang.common.domain.valueobject.Gender;
import com.project.imdang.common.domain.valueobject.OAuthProvider;
import com.project.imdang.member.domain.entity.Member;
import com.project.imdang.member.domain.valueobject.AccusePenaltyPolicy;

public interface MemberDomainService {

    Member join(Member member, String nickname, String birthDate, Gender gender, String deviceToken);

    Member createMember(String id, OAuthProvider oAuthProvider);
    Member accuseMember(Member member, AccusePenaltyPolicy accusePenaltyPolicy);

    Member logout(Member member);

    Member withdraw(Member member);

    Member storeRefreshToken(Member member, String refreshToken);
}
