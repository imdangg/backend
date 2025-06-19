package com.project.imdang.jwt;

import com.project.imdang.member.domain.ports.output.provider.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final TokenProvider tokenProvider;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // TODO - CHECK
        // /members, /members/info인 경우 필터 적용 X
        return request.getServletPath().equals("/members")
                || request.getServletPath().equals("/members/info");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        // 1. 헤더로부터 토큰 추출
//        // jwt == access_token
//        String jwt = request.getHeader(AUTHORIZATION_HEADER);
//        if (jwt == null) {
//            // TODO
//            throw new RuntimeException();
//        }
//
//        if (jwt.startsWith("Bearer")) {
//            jwt = jwt.substring(7);
//        }
//
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        SecretKey key = Keys.hmacShaKeyFor(keyBytes);
//        Claims claims = Jwts.parser()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(jwt)
//                .getBody();
//
//        // 2. 토큰이 있는 경우, 유효성 검증
//        try {
//            if (StringUtils.hasText(token) && tokenProvider.verifyToken(token)) {
//                // 3-1. 토큰 파싱해서 사용자 정보 가져오기
//                String memberId = null;
////                    String memberId = tokenProvider.extractSubject(token);
//
//                // 3-2. MemberId로 Authentication 정보 생성
//                Authentication auth = new UsernamePasswordAuthenticationToken(UUID.fromString(memberId), "", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
//                SecurityContextHolder.getContext().setAuthentication(auth);
//                log.debug("Authentication[id: {}] saved in Security Context", memberId);
//            } else {
//                request.setAttribute("exception", ADDITIONAL_REQUIRED_TOKEN.getErrorCode());
//                log.warn("JwtAuthFilter: Caught Exception {}", request.getAttribute("exception"));
//            }
//        } catch (MalformedJwtException e) {
//            request.setAttribute("exception", MAL_FORMED_TOKEN.getErrorCode());
//            log.warn("JwtAuthFilter: Caught MalformedJwtException {}", request.getAttribute("exception"), e);
//
//        } catch (ExpiredJwtException e) {
//            request.setAttribute("exception", EXPIRED_TOKEN.getErrorCode());
//            log.warn("JwtAuthFilter: Caught ExpiredJwtException {}", request.getAttribute("exception"), e);
//
//        } catch (UnsupportedJwtException e) {
//            request.setAttribute("exception", UNSUPPORTED_TOKEN.getErrorCode());
//            log.warn("JwtAuthFilter: Caught UnsupportedJwtException {}", request.getAttribute("exception"), e);
//
//        } catch (IllegalArgumentException e) {
//            request.setAttribute("exception", ILLEGAL_TOKEN.getErrorCode());
//            log.warn("JwtAuthFilter: Caught IllegalArgumentException {}", request.getAttribute("exception"), e);
//
//        } catch (Exception e) {
//            request.setAttribute("exception", UNKNOWN_ERROR.getErrorCode());
//            log.warn("JwtAuthFilter: Caught Exception {}", request.getAttribute("exception"), e);
//        }
//        log.info("spring context : {}",SecurityContextHolder.getContext().getAuthentication());


        filterChain.doFilter(request, response);
    }
}
