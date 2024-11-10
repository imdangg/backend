package com.project.imdang.member.service.domain.entity;

import com.project.imdang.domain.entity.AggregateRoot;
import com.project.imdang.domain.valueobject.MemberId;
import com.project.imdang.member.service.domain.valueobject.Gender;

public class Member extends AggregateRoot<MemberId> {

//    private String name;
    private String nickname;
    private String birthDate;
    private Gender gender;
    private String image;

//    private List<InsightId> insightIds;
//    private int insightCount;
    private int exchangeCount;

    public Member(String nickname, String birthDate, Gender gender, String image) {
        this.nickname = nickname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.image = image;
        this.exchangeCount = 0;
    }

/*
    private void setName(String name) {
        if (name.length() < 1) {
            throw new NameNotEnoughLengthException();
        }
        this.name = name;
    }*/

    private void setImage(String image) {
        this.image = image;
    }
}
