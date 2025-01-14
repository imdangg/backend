package com.project.imdang.member.service.domain.entity;

import com.project.imdang.domain.entity.AggregateRoot;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.valueobject.Gender;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Member extends AggregateRoot<MemberId> {

    private String nickname;
    private String birthDate;
    private Gender gender;
    private String deviceToken;

    private String oAuthId;
    private OAuthType oAuthType;
    private int exchangeCount;
    private int insightCount;
    // TODO - CHECK : 데이터를 쌓아서 GROUP BY로?
    private int rejectedCount;

    public static Member createNewMember(String oAuthId, OAuthType oAuthType) {
        return Member.builder()
                .id(new MemberId(UUID.randomUUID()))
                .oAuthId(oAuthId)
                .oAuthType(oAuthType)
                .build();
    }

    @Builder
    public Member(MemberId id, String nickname, String birthDate, Gender gender, String deviceToken, String oAuthId, OAuthType oAuthType, int exchangeCount,int insightCount, int rejectedCount) {
        setId(id);
        this.nickname = nickname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.deviceToken = deviceToken;
        this.oAuthId = oAuthId;
        this.oAuthType = oAuthType;
        this.exchangeCount = exchangeCount;
        this.insightCount = insightCount;
        this.rejectedCount = rejectedCount;
    }

    public void join(String nickname, String birthDate, Gender gender, String deviceToken) {
        this.nickname = nickname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.deviceToken = deviceToken;
    }
}
