package com.project.imdang.security;

import com.project.imdang.common.domain.valueobject.OAuthProvider;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

public final class OAuthAuthenticationToken extends AbstractAuthenticationToken {

    private final OAuthProvider oAuthProvider;
    private final String identifier;
    private final UUID memberId;

    public OAuthAuthenticationToken(OAuthProvider oAuthProvider, String identifier) {
        super(null);
        this.oAuthProvider = oAuthProvider;
        this.identifier = identifier;
        this.memberId = null;
        setAuthenticated(false);
    }

    public OAuthAuthenticationToken(UUID memberId, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.oAuthProvider = null;
        this.identifier = null;
        this.memberId = memberId;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        // TODO - 개선
        return this.memberId;
    }

    public OAuthProvider getOAuthProvider() {
        return oAuthProvider;
    }

    public String getIdentifier() {
        return identifier;
    }
}
