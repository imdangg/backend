package com.project.imdang.application.security;

import com.project.imdang.domain.jwt.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. 헤더로부터 토큰 추출
        String token = resolveToken(request);

        // 2. 토큰이 있는 경우, 유효성 검증
        try {

            if (StringUtils.hasText(token) && jwtTokenProvider.verifyToken(token)) {
                // 3-1. 토큰 파싱해서 사용자 정보 가져오기
                String memberId = jwtTokenProvider.extractSubject(token);
                // 3-2. 정보와 일치하는 사용자 가져오기
//                UserDetails userDetails = memberDetailsService.loadUserByUsername(memberId);
                // 3-3. Authentication 생성
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(UUID.fromString(memberId), null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } else {
                request.setAttribute("exception", ErrorCode.ADDITIONAL_REQUIRED_TOKEN.getErrorCode());
                log.info("JwtAuthFilter: Caught Exception {}", request.getAttribute("exception"));
            }

        } catch (SecurityException e) {
            request.setAttribute("exception", ErrorCode.UNKNOWN_ERROR.getErrorCode());
            log.info("JwtAuthFilter: Caught SecurityException {}", request.getAttribute("exception"));
        }
//        catch (MalformedJwtException e) {
//            request.setAttribute("exception", ErrorCode.MAL_FORMED_TOKEN.getErrorCode());
//            log.info("JwtAuthFilter: Caught MalformedJwtException {}", request.getAttribute("exception"));
//        } catch (ExpiredJwtException e) {
//            request.setAttribute("exception", ErrorCode.EXPIRED_TOKEN.getErrorCode());
//            log.info("JwtAuthFilter: Caught ExpiredJwtException {}", request.getAttribute("exception"));
//        } catch (UnsupportedJwtException e) {
//            request.setAttribute("exception", ErrorCode.UNSUPPORTED_TOKEN.getErrorCode());
//            log.info("JwtAuthFilter: Caught UnsupportedJwtException {}", request.getAttribute("exception"));
//        }
        catch (IllegalArgumentException e) {
            request.setAttribute("exception", ErrorCode.ILLEGAL_TOKEN.getErrorCode());
            log.info("JwtAuthFilter: Caught IllegalArgumentException {}", request.getAttribute("exception"));
        } catch (Exception e) {
            request.setAttribute("exception", ErrorCode.UNKNOWN_ERROR.getErrorCode());
            log.info("JwtAuthFilter: Caught Exception {}", request.getAttribute("exception"));
            log.info("Exception Message : {}", e.getMessage());
        }

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
