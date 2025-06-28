package com.project.imdang.common.domain.valueobject;

import java.util.Arrays;

public enum OAuthProvider {
    KAKAO, APPLE, GOOGLE, MOCK;

    public static OAuthProvider getProvider(String name) {
        return Arrays.stream(OAuthProvider.values())
                .filter(provider -> provider.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Illegal argument: " + name));
    }

}
