package com.project.imdang.member.persistence.mapper;


import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.domain.entity.Member;
import com.project.imdang.member.persistence.entity.MemberEntity;
import org.springframework.stereotype.Component;

@Component
public class MemberPersistenceMapper {
    public MemberEntity memberToMemberEntity(Member member) {
        return MemberEntity.builder()
                .id(member.getId().getValue())
                .oAuthId(member.getOAuthId())
                .oAuthProvider(member.getOAuthProvider())
                .nickname(member.getNickname())
                .birthDate(member.getBirthDate())
                .gender(member.getGender())
                .deviceToken(member.getDeviceToken())
                // TODO
//                .insightCount(null)
                .refreshToken(member.getRefreshToken())
                .isLogin(member.getIsLogin())
                .isDeleted(member.getIsDeleted())
                .latestInsightCreateDate(member.getLatestInsightCreateDate())
                .accusedCount(member.getAccusedCount())
                .status(member.getStatus())
                .penaltyPeriod(member.getPenaltyPeriod())
                .build();
    }

    public Member memberEntityToMember(MemberEntity memberEntity) {
        return Member.builder()
                .id(new MemberId(memberEntity.getId()))
                .oAuthId(memberEntity.getOAuthId())
                .oAuthProvider(memberEntity.getOAuthProvider())
                .nickname(memberEntity.getNickname())
                .birthDate(memberEntity.getBirthDate())
                .gender(memberEntity.getGender())
                .deviceToken(memberEntity.getDeviceToken())
                // TODO - CHECK
//                .insightCount(memberEntity.getInsightCount())
                .refreshToken(memberEntity.getRefreshToken())
                .isLogin(memberEntity.getIsLogin())
                .isDeleted(memberEntity.getIsDeleted())
                .latestInsightCreateDate(memberEntity.getLatestInsightCreateDate())
                .accusedCount(memberEntity.getAccusedCount())
                .status(memberEntity.getStatus())
                .penaltyPeriod(memberEntity.getPenaltyPeriod())
                .build();
    }
}
