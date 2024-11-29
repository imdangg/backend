package com.project.imdang.member.service.domain.dto.oauth.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginResponse;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleLoginResponse implements OAuthLoginResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("verified_email")
    private boolean isVerified;

    @JsonProperty("name")
    private String name;

    @Override
    public String getId() {
        return id;
    }
    @Override
    public String getNickname() {
        return name;
    }
    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public OAuthType oAuthType() {
        return OAuthType.GOOGLE;
    }
}