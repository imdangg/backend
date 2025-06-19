package com.project.imdang.member.domain;


import com.project.imdang.common.domain.valueobject.Gender;
import com.project.imdang.common.domain.valueobject.OAuthType;
import com.project.imdang.member.domain.entity.Member;
import com.project.imdang.member.domain.valueobject.AccusePenaltyPolicy;

public interface MemberDomainService {

    Member join(Member member, String nickname, String birthDate, Gender gender, String deviceToken);

    Member createMember(String id, OAuthType oAuthType);
    Member accuseMember(Member member, AccusePenaltyPolicy accusePenaltyPolicy);

    Member logout(Member member);

    Member withdraw(Member member);

    Member storeRefreshToken(Member member, String refreshToken);

    Member increaseInsightCount(Member member);
}
