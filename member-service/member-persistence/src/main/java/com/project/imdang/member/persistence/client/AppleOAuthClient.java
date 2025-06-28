package com.project.imdang.member.persistence.client;


import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.imdang.common.domain.valueobject.OAuthProvider;
import com.project.imdang.member.domain.ports.output.client.OAuthInfo;
import com.project.imdang.member.domain.ports.output.client.OAuthClient;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.Reader;
import java.io.StringReader;
import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Component
public class AppleOAuthClient implements OAuthClient {

    private final RestTemplate restTemplate;

    @Value("${oauth.apple.url.auth}")
    private String authUrl;

    @Value("${oauth.apple.url.withdraw-api}")
    private String revokeUrl;

    @Value("${oauth.apple.client-id}")
    private String clientId;

    @Value("${oauth.apple.login-key}")
    private String keyId;

    @Value("${oauth.apple.team-id}")
    private String teamId;

    @Value("${oauth.apple.key-path}")
    private String keyPath;

    @Override
    public OAuthProvider getOAuthProvider() {
        return OAuthProvider.APPLE;
    }

    /**
     * 엑세스 토큰과 리프레쉬토큰 얻어오기
     */
    @Override
    public OAuthInfo getOAuthInfo(String identifier) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("token", identifier);
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("client_secret", generateClientSecret());

        HttpEntity<?> request = new HttpEntity<>(body, headers);
        AppleTokenResponse response = restTemplate.postForObject(authUrl, request, AppleTokenResponse.class);

        DecodedJWT decodedJWT = JWT.decode(response.getIdToken());
        return AppleOAuthInfo.builder()
                .id(String.valueOf(decodedJWT.getClaim("sub")))
                .email(String.valueOf(decodedJWT.getClaim("email")))
                .refreshToken(response.getRefreshToken()).build();
    }

    @Override
    public void withdraw(String identifier) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("token", identifier);
        body.add("client_id", clientId);
        body.add("client_secret", generateClientSecret());
        body.add("token_type_hint", "refresh_token");

        HttpEntity<?> request = new HttpEntity<>(body, headers);
        restTemplate.postForObject(revokeUrl, request, Void.class);
    }

    private String generateClientSecret() {
        Map<String, Object> jwtHeader = new HashMap<>();
        jwtHeader.put("kid", keyId);
        jwtHeader.put("alg", "ES256");

        return Jwts.builder()
                .setHeaderParams(jwtHeader)
                .issuer(teamId)
                .audience().add("https://appleid.apple.com").and()
                .subject(clientId)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(Date.from(LocalDateTime.now().plusDays(30).atZone(ZoneId.systemDefault()).toInstant()))
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

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static class AppleOAuthInfo implements OAuthInfo {
        private String id;
        private String email;
        private String refreshToken;

        @Override
        public String getNickname() {
            return null;
        }

        @Override
        public String getEmail() {
            return email;
        }
        @Override
        public String getId() {
            return id;
        }

        public String getRefreshToken() {
            return refreshToken;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static class AppleTokenResponse {
        @JsonProperty("token_type") private String tokenType;
        @JsonProperty("access_token") private String accessToken;
        @JsonProperty("refresh_token") private String refreshToken;
        @JsonProperty("expires_in") private String expiresIn;
        @JsonProperty("id_token") private String idToken;
    }
}
