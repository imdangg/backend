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

//    private String name;
    private MemberId memberId;
    private String nickname;
    private String birthDate;
    private Gender gender;
//    private String image;

    private String oAuthId;
    private OAuthType oAuthType;

//    private List<InsightId> insightIds;
//    private int insightCount;
    private int exchangeCount;
    public static Member create(String oAuthId, OAuthType oAuthType) {
        return Member.builder()
                .memberId(new MemberId(UUID.randomUUID()))
                .oAuthId(oAuthId)
                .oAuthType(oAuthType)
                .build();
    }
    @Builder
    private Member(MemberId memberId, String oAuthId, OAuthType oAuthType) {
        this.memberId = memberId;
        this.oAuthId = oAuthId;
        this.oAuthType = oAuthType;
        this.exchangeCount = 0;
    }

    // TODO - CHECK : 데이터를 쌓아서 GROUP BY로?
    private int rejectedCount;

    @Builder
    public Member(String nickname, String birthDate, Gender gender) {
        this.nickname = nickname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.oAuthId = oAuthId;
        this.oAuthType = oAuthType;
        this.exchangeCount = 0;
    }

    public Member join(String nickname, String birthDate, String gender) {
        this.nickname = nickname;
        this.birthDate = birthDate;
        this.gender = Gender.getGenderType(gender);
        return this;
    }

    /*
    private void setName(String name) {
        if (name.length() < 1) {
            throw new NameNotEnoughLengthException();
        }
        this.name = name;
    }*/

    /*
    private void setImage(String image) {
        this.image = image;
    }
    */
}
