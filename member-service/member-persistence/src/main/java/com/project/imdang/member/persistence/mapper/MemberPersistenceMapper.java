package com.project.imdang.member.persistence.mapper;


import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.persistence.entity.MemberEntity;
import com.project.imdang.domain.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberPersistenceMapper {
    public MemberEntity memberToMemberEntity(Member member) {
        return MemberEntity.builder()
                .id(member.getId().getValue())
                .nickname(member.getNickname())
                .birthDate(member.getBirthDate())
                .gender(member.getGender())
                .authId(member.getOAuthId())
                .authType(member.getOAuthType())
                .build();
    }

    public Member memberEntityToMember(MemberEntity memberEntity) {
        return Member.builder()
                .id(new MemberId(memberEntity.getId()))
                .nickname(memberEntity.getNickname())
                .birthDate(memberEntity.getBirthDate())
                .gender(memberEntity.getGender())
                .oAuthId(memberEntity.getAuthId())
                .oAuthType(memberEntity.getAuthType())
                .build();
    }
}
