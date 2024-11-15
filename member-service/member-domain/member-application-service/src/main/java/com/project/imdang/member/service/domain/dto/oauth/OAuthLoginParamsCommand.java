package com.project.imdang.member.service.domain.dto.oauth;

import com.project.imdang.member.service.domain.valueobject.OAuthType;
import org.springframework.util.MultiValueMap;

public interface OAuthLoginParamsCommand {
    OAuthType oAuthType();
    MultiValueMap<String, String> makeBody();
}
