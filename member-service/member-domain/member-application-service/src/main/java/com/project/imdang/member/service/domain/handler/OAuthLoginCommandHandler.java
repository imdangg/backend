package com.project.imdang.member.service.domain.handler;

import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginCommand;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginResponse;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OAuthLoginCommandHandler {
    private final Map<OAuthType, OAuthApiClientHandler> apiClients;

    public OAuthLoginCommandHandler(List<OAuthApiClientHandler> apiClients) {
        this.apiClients = apiClients.stream()
                .collect(Collectors.toUnmodifiableMap(OAuthApiClientHandler::oAuthType, Function.identity()));
    }

    public OAuthLoginResponse request(OAuthLoginCommand loginCommand) {
        OAuthApiClientHandler client = apiClients.get(loginCommand.oAuthType());
        String accessToken = client.getAccessToken(loginCommand);
        return client.getOAuthInfo(accessToken);
    }
}
