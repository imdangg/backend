package com.project.imdang.member.service.persistence.mapper;


import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.entity.Member;
import com.project.imdang.member.service.persistence.entity.MemberEntity;
import org.springframework.stereotype.Component;

@Component
public class MemberPersistenceMapper {
    public MemberEntity memberToMemberEntity(Member member) {
        return MemberEntity.builder()
                .id(member.getId().getValue())
                .authId(member.getOAuthId())
                .authType(member.getOAuthType())
                .nickname(member.getNickname())
                .birthDate(member.getBirthDate())
                .gender(member.getGender())
                .deviceToken(member.getDeviceToken())
                .insightCount(member.getInsightCount())
                .exchangeCount(member.getExchangeCount())
                .rejectedCount(member.getRejectedCount())
                .refreshToken(member.getRefreshToken())
                .isLogin(member.getIsLogin())
                .isDeleted(member.getIsDeleted())
                .isCouponReceived(member.getIsCouponReceived())
                .accusedCount(member.getAccusedCount())
                .status(member.getStatus())
                .penaltyPeriod(member.getPenaltyPeriod())
                .build();
    }

    public Member memberEntityToMember(MemberEntity memberEntity) {
        return Member.builder()
                .id(new MemberId(memberEntity.getId()))
                .oAuthId(memberEntity.getAuthId())
                .oAuthType(memberEntity.getAuthType())
                .nickname(memberEntity.getNickname())
                .birthDate(memberEntity.getBirthDate())
                .gender(memberEntity.getGender())
                .deviceToken(memberEntity.getDeviceToken())
                .insightCount(memberEntity.getInsightCount())
                .exchangeCount(memberEntity.getExchangeCount())
                .rejectedCount(memberEntity.getRejectedCount())
                .refreshToken(memberEntity.getRefreshToken())
                .isLogin(memberEntity.getIsLogin())
                .isDeleted(memberEntity.getIsDeleted())
                .isCouponReceived(memberEntity.getIsCouponReceived())
                .accusedCount(memberEntity.getAccusedCount())
                .status(memberEntity.getStatus())
                .penaltyPeriod(memberEntity.getPenaltyPeriod())
                .build();
    }
}
