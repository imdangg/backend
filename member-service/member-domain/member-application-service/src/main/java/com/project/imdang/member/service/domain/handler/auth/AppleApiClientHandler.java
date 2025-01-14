package com.project.imdang.member.service.domain.handler.auth;


import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginCommand;
import com.project.imdang.member.service.domain.dto.oauth.OAuthLoginResponse;
import com.project.imdang.member.service.domain.dto.oauth.apple.AppleLoginResponse;
import com.project.imdang.member.service.domain.dto.oauth.apple.AppleTokenResponse;
import com.project.imdang.member.service.domain.valueobject.OAuthType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.Reader;
import java.io.StringReader;
import java.security.PrivateKey;
import java.util.Date;


@Component
@RequiredArgsConstructor
public class AppleApiClientHandler implements OAuthApiClientHandler {
    private final RestTemplate restTemplate;
    private static final String GRANT_TYPE = "authorization_code";

    @Value("${oauth.apple.url.auth}")
    private String authUrl;

    @Value("${oauth.apple.client-id}")
    private String clientId;

    @Value("${oauth.apple.login-key}")
    private String keyId;

    @Value("${oauth.apple.team-id}")
    private String teamId;

    @Value("${oauth.apple.key-path}")
    private String keyPath;
    @Override
    public OAuthType oAuthType() {
        return OAuthType.APPLE;
    }


    /**
     * 엑세스 토큰과 리프레쉬토큰 얻어오기
     */
    @Override
    public String getAccessToken(OAuthLoginCommand loginCommand) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = loginCommand.makeBody();
        body.add("grant_type", GRANT_TYPE);
        body.add("client_id", clientId);
        body.add("client_secret", generateClientSecret());

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);
        AppleTokenResponse response = restTemplate.postForObject(authUrl, request, AppleTokenResponse.class);
        //TODO null 체크
        return response.getIdToken();
    }

    @Override
    public OAuthLoginResponse getOAuthInfo(String idToken) {
        DecodedJWT decodedJWT = JWT.decode(idToken);
        return AppleLoginResponse.builder()
                .id(String.valueOf(decodedJWT.getClaim("sub")))
                .email(String.valueOf(decodedJWT.getClaim("email"))).build();
    }

    private String generateClientSecret() {
        return Jwts.builder()
                .header().keyId(keyId)
                .and()
                .issuer(teamId)
                .audience().add(authUrl).and()
                .subject(clientId)
                .issuedAt(new Date())
                .expiration(new Date(1000*60))
                .signWith(getPrivateKey(), SignatureAlgorithm.ES256)
                .compact();
    }

    private PrivateKey getPrivateKey() {
        ClassPathResource resource = new ClassPathResource(keyPath);
        try {
            String privateKey = new String(resource.getInputStream().readAllBytes());
            Reader pemReader = new StringReader(privateKey);
            PEMParser pemParser = new PEMParser(pemReader);
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
            PrivateKeyInfo privateKeyInfo = (PrivateKeyInfo) pemParser.readObject();
            return converter.getPrivateKey(privateKeyInfo);

        } catch (Exception e) {
            throw new RuntimeException("Error converting private key from String", e);
        }
    }
}
