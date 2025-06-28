package com.project.imdang.member.domain.ports.output.token;

import java.util.Date;
import java.util.Map;

public interface TokenProvider {

    String generate(String subject, Map<String, Object> claims, Date expiration);
    void validate(String token);

    String extractSubject(String token);
    Map<String, Object> extractClaims(String token);
}
