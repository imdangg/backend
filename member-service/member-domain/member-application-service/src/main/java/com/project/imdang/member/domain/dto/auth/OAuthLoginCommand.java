package com.project.imdang.member.domain.dto.auth;

import com.project.imdang.common.domain.valueobject.OAuthType;
import org.springframework.util.MultiValueMap;

public interface OAuthLoginCommand {
    OAuthType oAuthType();
    MultiValueMap<String, String> makeBody();
}
