package com.project.imdang.member.service.domain.dto.oauth.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
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
