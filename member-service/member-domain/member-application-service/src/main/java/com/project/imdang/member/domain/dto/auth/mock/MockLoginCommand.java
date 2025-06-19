package com.project.imdang.member.domain.dto.auth.mock;

import com.project.imdang.member.domain.dto.auth.OAuthLoginCommand;
import com.project.imdang.common.domain.valueobject.OAuthType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MockLoginCommand implements OAuthLoginCommand {
    private String id;

    @Override
    public OAuthType oAuthType() {
        return OAuthType.MOCK;
    }

    @Override
    public MultiValueMap<String, String> makeBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("id", id);
        return body;
    }
}
