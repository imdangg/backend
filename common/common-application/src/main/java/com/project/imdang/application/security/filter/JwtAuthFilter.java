package com.project.imdang.application.security.filter;

import com.project.imdang.application.security.util.ErrorCode;
import com.project.imdang.domain.jwt.JwtTokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final JwtTokenProvider jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //1. 헤더로부터 토큰 추출
        String token = resolveToken(request);

        // 2. 토큰이 있는 경우, 유효성 검증
        try {
            if (StringUtils.hasText(token) && jwtTokenUtil.verifyToken(token)) {
                // 3-1. 토큰 파싱해서 사용자 정보 가져오기
                String memberId = jwtTokenUtil.extractSubject(token);

                // 3-2. MemberId로 Authentication 정보 생성
                Authentication auth = new UsernamePasswordAuthenticationToken(UUID.fromString(memberId), "", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
                SecurityContextHolder.getContext().setAuthentication(auth);
                log.debug("Authentication[id:{}] saved in Security Context", memberId);
            }
            else {
                request.setAttribute("exception", ErrorCode.ADDITIONAL_REQUIRED_TOKEN.getErrorCode());
                log.info("JwtAuthFilter: Caught Exception {}", request.getAttribute("exception"));
            }
        } catch (SecurityException | MalformedJwtException e) {
            request.setAttribute("exception", ErrorCode.MAL_FORMED_TOKEN.getErrorCode());
            log.info("JwtAuthFilter: Caught MalformedJwtException {}", request.getAttribute("exception"));

        } catch (ExpiredJwtException e) {
            request.setAttribute("exception", ErrorCode.EXPIRED_TOKEN.getErrorCode());
            log.info("JwtAuthFilter: Caught ExpiredJwtException {}", request.getAttribute("exception"));

        } catch (UnsupportedJwtException e) {
            request.setAttribute("exception", ErrorCode.UNSUPPORTED_TOKEN.getErrorCode());
            log.info("JwtAuthFilter: Caught UnsupportedJwtException {}", request.getAttribute("exception"));

        } catch (IllegalArgumentException e) {
            request.setAttribute("exception", ErrorCode.ILLEGAL_TOKEN.getErrorCode());
            log.info("JwtAuthFilter: Caught IllegalArgumentException {}", request.getAttribute("exception"));

        } catch (Exception e) {
            request.setAttribute("exception", ErrorCode.UNKNOWN_ERROR.getErrorCode());
            log.info("JwtAuthFilter: Caught Exception {}", request.getAttribute("exception"));
            log.info("Exception Message : {}", e.getMessage());
        }
        log.info("spring context : {}",SecurityContextHolder.getContext().getAuthentication());
        filterChain.doFilter(request, response);
    }
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
