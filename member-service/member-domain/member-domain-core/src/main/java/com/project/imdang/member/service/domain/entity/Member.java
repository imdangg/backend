package com.project.imdang.member.service.domain.entity;

import com.project.imdang.domain.entity.AggregateRoot;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.valueobject.Gender;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
import lombok.Builder;
import lombok.Getter;

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

    @Builder
    public Member(MemberId memberId, String nickname, String birthDate, Gender gender, String oAuthId, OAuthType oAuthType) {
        this.memberId = memberId;

    // TODO - CHECK : 데이터를 쌓아서 GROUP BY로?
    private int rejectedCount;

    @Builder
    public Member(String nickname, String birthDate, Gender gender, String image) {
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
