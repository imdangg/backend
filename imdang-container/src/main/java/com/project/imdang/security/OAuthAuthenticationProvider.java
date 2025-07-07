package com.project.imdang.security;

import com.project.imdang.common.domain.valueobject.Gender;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.common.domain.valueobject.MemberStatus;
import com.project.imdang.common.domain.valueobject.OAuthProvider;
import com.project.imdang.member.domain.MemberDomainService;
import com.project.imdang.member.domain.entity.Member;
import com.project.imdang.member.domain.handler.MemberHelper;
import com.project.imdang.member.domain.handler.auth.TokenHandler;
import com.project.imdang.member.domain.ports.output.client.OAuthClientHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class OAuthAuthenticationProvider implements AuthenticationProvider {

    private static final MemberId MEMBER_ID = new MemberId(UUID.fromString("b8654f0d-3bf6-479d-b729-6901c2f83c3a"));

    // TODO - 개선
    private final MemberDomainService memberDomainService;
    private final MemberHelper memberHelper;
    private final OAuthClientHandler oAuthClientHandler;
    private final TokenHandler tokenHandler;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        OAuthAuthenticationToken oAuthAuthenticationToken = (OAuthAuthenticationToken) authentication;
        final OAuthProvider oAuthProvider = oAuthAuthenticationToken.getOAuthProvider();
        final String identifier = oAuthAuthenticationToken.getIdentifier();
//        OAuthInfo oAuthInfo = oAuthClientHandler.getOAuthInfo(oAuthProvider, identifier);

        // 로그인
//        Optional<Member> optional = memberHelper.getByOAuthIdAndOAuthProviderAndIsDeleted(oAuthInfo.getId(), oAuthProvider, Boolean.FALSE);
        Optional<Member> optional = Optional.of(Member.builder()
                        .id(MEMBER_ID)
                        .oAuthId("oAuthId")
                        .oAuthProvider(OAuthProvider.GOOGLE)
                        .nickname("nickname")
                        .birthDate("20250708")
                        .gender(Gender.FEMALE)
                        .deviceToken("device-token")
                        .isDeleted(false)
                        .status(MemberStatus.ACTIVE)
                .build());
        // deleted 되지 않은 사용자라면 가져오고, 아니라면 새로 생성
        Member member;
//        if (optional.isEmpty()) {
//            member = memberDomainService.createMember(oAuthInfo.getId(), oAuthProvider);
//        } else {
            member = optional.get();
//        }

        final UUID memberId = member.getId().getValue();
        // generate refresh_token
        final String refreshToken = tokenHandler.generateRefreshToken(memberId);
        memberDomainService.storeRefreshToken(member, refreshToken);
        memberHelper.save(member);
        return new OAuthAuthenticationToken(memberId, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OAuthAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
