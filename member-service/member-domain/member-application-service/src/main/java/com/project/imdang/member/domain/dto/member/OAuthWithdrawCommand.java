package com.project.imdang.member.domain.dto.member;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.common.domain.valueobject.OAuthType;
import org.springframework.util.MultiValueMap;

public interface OAuthWithdrawCommand {
    MemberId getMemberId();
    OAuthType getOAuthType();
    MultiValueMap<String, String> getBody();
}
