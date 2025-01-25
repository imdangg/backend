package com.project.imdang.member.service.domain.dto.oauth;

import com.project.imdang.member.service.domain.valueobject.OAuthType;
import org.springframework.util.MultiValueMap;

public interface OAuthWithdrawCommand {
    OAuthType oAuthType();
    MultiValueMap<String, String> makeBody();
}