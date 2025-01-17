package com.project.imdang.member.persistence.mapper;


import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.persistence.entity.MemberEntity;
import com.project.imdang.member.service.domain.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberPersistenceMapper {
    public MemberEntity memberToMemberEntity(Member member) {
        return MemberEntity.builder()
                .id(member.getId().getValue())
                .nickname(member.getNickname())
                .birthDate(member.getBirthDate())
                .gender(member.getGender())
                .deviceToken(member.getDeviceToken())
                .authId(member.getOAuthId())
                .authType(member.getOAuthType())
                .insightCount(member.getInsightCount())
                .exchangeCount(member.getExchangeCount())
                .build();
    }

    public Member memberEntityToMember(MemberEntity memberEntity) {
        return Member.builder()
                .id(new MemberId(memberEntity.getId()))
                .nickname(memberEntity.getNickname())
                .birthDate(memberEntity.getBirthDate())
                .gender(memberEntity.getGender())
                .deviceToken(memberEntity.getDeviceToken())
                .oAuthId(memberEntity.getAuthId())
                .oAuthType(memberEntity.getAuthType())
                .exchangeCount(memberEntity.getExchangeCount())
                .insightCount(memberEntity.getInsightCount())
                .build();
    }
}
