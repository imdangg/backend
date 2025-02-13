package com.project.imdang.member.service.domain.valueobject;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Gender {
    MALE("남자"), FEMALE("여자");

    private final String name;

    public static Gender getType(String name) {
        return Arrays.stream(Gender.values())
                .filter(gender -> gender.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Illegal argument: " + name));
    }
}
