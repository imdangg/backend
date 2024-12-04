package com.project.imdang.member.service.domain.valueobject;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Gender {
    MALE("남자"), FEMALE("여자");

    private final String genderName;

    public static Gender getGenderType(String genderName) {
        //TODO : 예외처리
        return Arrays.stream(Gender.values())
                .filter(creditCard -> genderName.equals(genderName))
                .findFirst()
                .orElseThrow();
    }
}
