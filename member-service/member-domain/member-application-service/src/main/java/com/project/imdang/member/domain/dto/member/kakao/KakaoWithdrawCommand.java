package com.project.imdang.member.domain.dto.member.kakao;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.common.domain.valueobject.OAuthType;
import com.project.imdang.member.domain.dto.member.OAuthWithdrawCommand;
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
public class KakaoWithdrawCommand implements OAuthWithdrawCommand {

    private MemberId memberId;
    private String token;

    @Override
    public OAuthType getOAuthType() {
        return OAuthType.KAKAO;
    }

    @Override
    public MultiValueMap<String, String> getBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("token", token);
        return body;
    }
}
