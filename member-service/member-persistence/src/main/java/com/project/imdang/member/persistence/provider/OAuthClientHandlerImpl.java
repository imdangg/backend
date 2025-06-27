package com.project.imdang.member.persistence.provider;

import com.project.imdang.common.domain.valueobject.OAuthProvider;
import com.project.imdang.member.domain.ports.output.client.OAuthClientHandler;
import com.project.imdang.member.domain.ports.output.client.OAuthInfo;
import com.project.imdang.member.domain.ports.output.client.OAuthClient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class OAuthClientHandlerImpl implements OAuthClientHandler {

    private final List<OAuthClient> oAuthClients;
    private Map<OAuthProvider, OAuthClient> oAuthClientMap;

    @PostConstruct
    public void initialize() {
        this.oAuthClientMap = oAuthClients.stream()
                .collect(Collectors.toUnmodifiableMap(OAuthClient::getProvider, Function.identity()));
    }

    @Override
    public OAuthInfo getOAuthInfo(OAuthProvider oAuthProvider, String identifier) {
        final OAuthClient oAuthClient = oAuthClientMap.get(oAuthProvider);
        return oAuthClient.getOAuthInfo(identifier);
    }

    @Override
    public void withdraw(OAuthProvider oAuthProvider, String identifier) {
        final OAuthClient oAuthClient = oAuthClientMap.get(oAuthProvider);
        oAuthClient.withdraw(identifier);
    }
}
