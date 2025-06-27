package com.project.imdang.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

import static com.project.imdang.common.application.constant.Header.AUTHORIZATION;
import static com.project.imdang.common.application.constant.RequestPath.LOGIN;


@Slf4j
@RequiredArgsConstructor
public class JwtGenerateFilter extends OncePerRequestFilter {
    // TODO - CHECK: GenericFilterBean
    // Environment env = getEnvironment();
    @Value("${jwt.secret-key}")
    private String secretKey;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // false: 실행
        String servletPath = request.getServletPath();
        return !servletPath.startsWith(LOGIN);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 인증 후
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
            final String jwt = Jwts.builder()
                    .subject("jwt")
                    .claim("username", authentication.getName())
                    .claim("authorities", authentication.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                    .issuedAt(new Date())
                    .expiration(new Date(new Date().getTime() + 1000 * 60 * 60 * 5))
                    .signWith(key)
                    .compact();
            response.setHeader(AUTHORIZATION, jwt);
        }
        filterChain.doFilter(request, response);
    }
}
