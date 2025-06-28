package com.project.imdang.security;

import com.project.imdang.member.domain.handler.auth.TokenHandler;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import static com.project.imdang.common.application.constant.Header.AUTHORIZATION;
import static com.project.imdang.common.application.constant.RequestPath.DETAIL_MEMBER;
import static com.project.imdang.common.application.constant.RequestPath.LIST_MEMBER;
import static com.project.imdang.common.application.constant.RequestPath.LOGIN;


@Slf4j
@RequiredArgsConstructor
public class AccessTokenValidateFilter extends OncePerRequestFilter {

    private final TokenHandler tokenHandler;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // false: 실행
        final String servletPath = request.getServletPath();
        // TODO - 제거
        if (servletPath.equals(DETAIL_MEMBER)
                || servletPath.equals(LIST_MEMBER)) {
            return true;
        }
        return servletPath.startsWith(LOGIN);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String jwt = request.getHeader(AUTHORIZATION);
        if (jwt != null && jwt.startsWith("Bearer")) {

            try {

                final String accessToken = jwt.substring(7);
                Map<String, Object> claims = tokenHandler.validateAndExtractClaimsFromAccessToken(accessToken);
                String _memberId = String.valueOf(claims.get("memberId"));
                final UUID memberId = UUID.fromString(_memberId);

                // TODO
//                String authorities = String.valueOf(claims.get("authorities"));
//                List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                Authentication authentication = new OAuthAuthenticationToken(memberId, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                throw new BadCredentialsException("Invalid token received!");
            }

        }
        filterChain.doFilter(request, response);
    }


}
