package com.project.imdang.member.persistence.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.imdang.common.domain.valueobject.OAuthType;
import lombok.*;

@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KakaoWithdrawResponse{

    @JsonProperty("id")
    private String id;

    public OAuthType getOAuthType() {
        return OAuthType.KAKAO;
    }
}
